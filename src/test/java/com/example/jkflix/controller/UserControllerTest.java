package com.example.jkflix.controller;

import com.example.jkflix.exception.AlreadyExistsEmailException;
import com.example.jkflix.exception.InvalidSigninInformation;
import com.example.jkflix.mapper.UserMapper;
import com.example.jkflix.request.Login;
import com.example.jkflix.request.Signup;
import com.example.jkflix.service.Userservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private Userservice userservice;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void clean() {userMapper.deleteUserAll(); }

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        Signup signup = Signup.builder()
                .email("test1@test1.com")
                .password("1234")
                .nickName("닉네임1")
                .introduce("하이하이")
                .build();

        // when
        userservice.signup(signup);

        // then
        Assertions.assertEquals(1, userMapper.findUserAllNum());

        Signup signupUser = userMapper.findUserAll();
        assertEquals("test1@test1.com", signupUser.getEmail());
        assertNotNull(signupUser.getPassword());
        assertEquals("닉네임1", signupUser.getNickName());
    }

    @Test
    @DisplayName("중복 회원입니다. ")
    void test2() {
        // given
        Signup signup1 = Signup.builder()
                .email("test1@test1.com")
                .password("1234")
                .nickName("닉네임1")
                .introduce("하이하이")
                .build();
        userservice.signup(signup1);

        Signup signup2 = Signup.builder()
                .email("test1@test1.com")
                .password("1234")
                .nickName("닉네임1")
                .introduce("하이하이")
                .build();

        // expected
        Assertions.assertThrows(AlreadyExistsEmailException.class, () -> userservice.signup(signup2));
    }

    @Test
    @DisplayName("로그인 성공")
    void test3() {
        // given
        // 임의의 회원 저장
        Signup user = Signup.builder()
                .email("test1@test1.com")
                .password("1234")
                .nickName("닉네임1")
                .introduce("하하하하")
                .build();
        userservice.signup(user); // signup 과정에서 암호화 해줌

        // 로그인 회원 정보 생성
        Login login = Login.builder()
                .email("test1@test1.com")
                .password("1234")
                .build();

        // when
        Long userId = userservice.signin(login);

        // then
        assertNotNull(userId);
    }

    @Test
    @DisplayName("비밀번호 틀림")
    void test4() {
        // given
        // 임의의 회원 저장
        Signup user = Signup.builder()
                .email("test1@test1.com")
                .password("1234")
                .nickName("닉네임1")
                .introduce("하하하하")
                .build();
        userservice.signup(user); // signup 과정에서 암호화 해줌

        // 로그인 회원 정보 생성
        Login login = Login.builder()
                .email("test1@test1.com")
                .password("8888")
                .build();

        // then
        assertThrows(InvalidSigninInformation.class, () -> userservice.signin(login));
    }

    @Test
    @DisplayName("내 정보 가져오기")
    void test5() {
        // given
//        PostCreate request = PostCreate.builder()
//                .content("내용입니다.")
//                .build();
//
//        String json = objectMapper.writeValueAsString(request);
//
//        // expected
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.code").value("400"))
//                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력하세요."))
//                .andDo(print());
    }


}