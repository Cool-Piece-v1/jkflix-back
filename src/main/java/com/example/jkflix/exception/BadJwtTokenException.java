package com.example.jkflix.exception;

public class BadJwtTokenException extends KwonException{

    private static final String MESSAGE = "JWT토큰이 없거나 잘못된 JWT토큰입니다.";

    public BadJwtTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
