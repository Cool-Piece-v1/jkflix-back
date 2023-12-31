package com.example.jkflix.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
public class Login {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    // 생성자

    @Builder
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
