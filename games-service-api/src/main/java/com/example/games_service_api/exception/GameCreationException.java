package com.example.games_service_api.exception;

import lombok.Getter;

@Getter
public class GameCreationException extends RuntimeException {
    private final String errorCode;

    public GameCreationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GameCreationException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}