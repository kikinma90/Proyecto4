package com.example.games_service_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGameNotFoundException(GameNotFoundException ex) {
        log.error("Game not found: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.NOT_FOUND.value()
        );
        errorResponse.put("gameId", ex.getGameId());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedGameAccessException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedGameAccessException(UnauthorizedGameAccessException ex) {
        log.error("Unauthorized game access: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                "Access denied to the requested game",
                ex.getErrorCode(),
                HttpStatus.FORBIDDEN.value()
        );
        errorResponse.put("gameId", ex.getGameId());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GameCreationException.class)
    public ResponseEntity<Map<String, Object>> handleGameCreationException(GameCreationException ex) {
        log.error("Game creation failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GameUpdateException.class)
    public ResponseEntity<Map<String, Object>> handleGameUpdateException(GameUpdateException ex) {
        log.error("Game update failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        errorResponse.put("gameId", ex.getGameId());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GameDeletionException.class)
    public ResponseEntity<Map<String, Object>> handleGameDeletionException(GameDeletionException ex) {
        log.error("Game deletion failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        errorResponse.put("gameId", ex.getGameId());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Invalid argument: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                "INVALID_INPUT",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = createErrorResponse(
                "An unexpected error occurred. Please try again later.",
                "INTERNAL_SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> createErrorResponse(String message, String errorCode, int statusCode) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", statusCode);
        errorResponse.put("error", message);
        errorResponse.put("errorCode", errorCode);
        return errorResponse;
    }
}
