package com.example.user_service_api.Exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String errorCode;
    private final Long userId;

    public UserNotFoundException(String message, String errorCode, Long userId) {
        super(message);
        this.errorCode = errorCode;
        this.userId = userId;
    }

    public UserNotFoundException(String message, String errorCode, Long userId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userId = userId;
    }
}