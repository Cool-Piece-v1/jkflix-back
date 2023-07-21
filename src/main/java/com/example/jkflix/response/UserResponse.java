package com.example.jkflix.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String introduce;


}
