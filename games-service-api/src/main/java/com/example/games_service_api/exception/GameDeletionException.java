package com.example.games_service_api.exception;

import lombok.Getter;

@Getter
public class GameDeletionException extends RuntimeException {
    private final String errorCode;
    private final Long gameId;
    private final String userId;

    public GameDeletionException(String message, String errorCode, Long gameId, String userId) {
        super(message);
        this.errorCode = errorCode;
        this.gameId = gameId;
        this.userId = userId;
    }

    public GameDeletionException(String message, String errorCode, Long gameId, String userId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.gameId = gameId;
        this.userId = userId;
    }
}