package com.example.user_service_api.Exception;

import lombok.Getter;

@Getter
public class UserUpdateException extends RuntimeException {
    private final String errorCode;
    private final Long userId;

    public UserUpdateException(String message, String errorCode, Long userId) {
        super(message);
        this.errorCode = errorCode;
        this.userId = userId;
    }

    public UserUpdateException(String message, String errorCode, Long userId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userId = userId;
    }
}