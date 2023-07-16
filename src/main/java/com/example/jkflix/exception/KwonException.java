package com.example.jkflix.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class KwonException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    public KwonException(String message) {
        super(message);
    }

    public KwonException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }




}
