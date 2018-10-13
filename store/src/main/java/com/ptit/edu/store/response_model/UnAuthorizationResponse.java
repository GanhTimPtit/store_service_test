package com.ptit.edu.store.response_model;

import org.springframework.http.HttpStatus;

public class UnAuthorizationResponse extends Response {
    public UnAuthorizationResponse(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public <T> UnAuthorizationResponse(String message, T data) {
        super(HttpStatus.UNAUTHORIZED, message, data);
    }
}
