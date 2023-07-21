package com.example.jkflix.mapper;

import com.example.jkflix.request.Signup;
import com.example.jkflix.response.LikeResponse;
import com.example.jkflix.response.UserResponse;
import com.example.jkflix.response.UserSession;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    
    // 회원가입 - 중복 이메일 주소 확인
    @Select("""
            SELECT * FROM User Where email = #{email}
            """)
    Signup findByEmail(String email);

    // 회원가입 - 회원가입
    @Insert("""
            INSERT INTO User
                (email,
                nickName,
                introduce,
                password
                )
            VALUES
                (
                #{email},
                #{nickName},
                #{introduce},
                #{password}
                )
            """)
    void signup(Signup user);

    // 회원가입 - User 테이블 정보 삭제 (For Test)
    @Delete("""
            DELETE FROM User
            """)
    void deleteUserAll();

    // 회원가입 - User 테이블 row count (For Test)
    @Select("""
            SELECT COUNT(*) FROM User
            """)
    int findUserAllNum();

    // 회원가입 - User 테이블 전체 조회
    @Select("""
            SELECT * FROM User
            """)
    Signup findUserAll();

    @Select("""
            SELECT *
            FROM User
            where id = #{id}
            """)
    UserResponse getMyData(Long id);

    @Select("""
            SELECT *
            FROM Likes
            where userid = #{userId}
            """)
    List<LikeResponse> getMyLike(Long userId);
}
