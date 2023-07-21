package com.example.jkflix.response;

import lombok.Getter;

@Getter
public class SessionRes {

    private final String accessToken;

    public SessionRes(String accessToken) {
        this.accessToken = accessToken;
    }
}
