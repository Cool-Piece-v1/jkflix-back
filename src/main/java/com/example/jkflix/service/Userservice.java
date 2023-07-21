package com.example.jkflix.service;

import com.example.jkflix.encode.PasswordEncoder;
import com.example.jkflix.exception.AlreadyExistsEmailException;
import com.example.jkflix.exception.InvalidSigninInformation;
import com.example.jkflix.mapper.UserMapper;
import com.example.jkflix.request.Login;
import com.example.jkflix.request.Signup;
import com.example.jkflix.response.LikeRes;
import com.example.jkflix.response.MovieDetailsRes;
import com.example.jkflix.response.UserRes;
import com.example.jkflix.tmdb.service.TmdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class Userservice {

    // User관련 정보를 담은 UserMapper 주입
    private final UserMapper userMapper;

    // user 정보 중 like와 view를 가져올 때 사용할 내용 ==> api와 user가 겹치는 부분.
    private final TmdbService tmdbService;

    // 회원가입 메서드 작성
    public void signup(Signup signup) {

        // 일단 중복되는 이메일이 없을 때 기능하도록 하며 이메일이 있을 시 오류 반환
        Signup userOptional = userMapper.findByEmail(signup.getEmail());

        if (userOptional != null) {
            throw new AlreadyExistsEmailException(); // 정해준 예외처리로 던짐
        }

        // 비밀번호를 암호화 해줄 PasswordEncoder 클래스를 재정의 해주고 비밀번호 암호화
        PasswordEncoder encoder = new PasswordEncoder();
        String encryptedPassword = encoder.encrpyt(signup.getPassword());

        // 빌더 패턴을 사용하여 user의 값 대입
        // 빌더 패턴을 사용하는 이유
        // 1. 생성자를 사용했을 시, 인자의 위치에 구애받지 않음
        var user = Signup.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .nickName(signup.getNickName())
                .introduce(signup.getIntroduce())
                .build();

        userMapper.signup(user);
    }

    public Long signin(Login login) {

        // 로그인한 Email로 로그인된 값이 있는지 확인
        Signup user = userMapper.findByEmail(login.getEmail());

        if (user == null) { // 이메일이 없을 때, 오류메시지 던짐
            throw new InvalidSigninInformation();
        }

        // 이메일이 존재할 때, 입력한 비밀번호와 암호화된 비밀번호를 비교하는 과정
        PasswordEncoder encoder = new PasswordEncoder();
        var matches = encoder.matches(login.getPassword(), user.getPassword());
        if (!matches) { // 안맞을 시, 오류 메시지 던짐
            throw new InvalidSigninInformation();
        }

        return user.getId();
    }

    public Map<String, Object> getMyData(Long id) {

        // 정보를 넣어줄 Map <- likeMap
        Map<String, Object> result = new HashMap<>();

        // 1. 개인 정보 전달
        UserRes myDataList = userMapper.getMyData(id);
        result.put("myDataList", myDataList);

        // 2. Like 갯수 및 정보 전달
        List<LikeRes> myLikeList = userMapper.getMyLike(id);
        result.put("myLikeListNumber", myLikeList.size());
        result.put("myLikeList", myLikeList);

        List<MovieDetailsRes> likeMovieDetails = new ArrayList<>();

        if (myLikeList.size() > 0) {
            for (int i = 0; i < myLikeList.size(); i++) {
                var likeContentId = myLikeList.get(i).getContentId();
                var movieDetails = tmdbService.searchContentDetail(likeContentId);

                likeMovieDetails.add(movieDetails);
            }
        }

        result.put("likeMovieDetails", likeMovieDetails);

        return result;
    }
}