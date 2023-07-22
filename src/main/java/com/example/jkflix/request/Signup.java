package com.example.jkflix.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Signup {

    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String passwordCheck;
    private String introduce;

    private int like;
    private int view;




}
