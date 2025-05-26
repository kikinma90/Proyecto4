package com.example.games_service_api.exception;

import lombok.Getter;

@Getter
public class GameNotFoundException extends RuntimeException {
    private final String errorCode;
    private final Long gameId;

    public GameNotFoundException(String message, String errorCode, Long gameId) {
        super(message);
        this.errorCode = errorCode;
        this.gameId = gameId;
    }

    public GameNotFoundException(String message, String errorCode, Long gameId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.gameId = gameId;
    }
}