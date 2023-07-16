package com.example.jkflix.encode;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class PasswordEncoder {

    // 회원가입 시 , Encoder를 통하여 비밀번호를 암호화 해주는 클래스
    // SCrypt 방법을 사용함

    private static final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(
            16,
            8,
            1,
            32,
            64
    );

    // 생성자 --> 인자로 들어간 입력 password를 SCrypt 방법으로 encode함
    public String encrpyt(String rawPassword) {
            return encoder.encode(rawPassword);
    }

    // 로그인 시 , matches 메서드를 통하여 입력한 값과 그것을 복호화한 값이 같은지 다른지 판별
    public boolean matches(String rawPassword, String encrytedPassword) {
        return encoder.matches(rawPassword, encrytedPassword);
    }

}
