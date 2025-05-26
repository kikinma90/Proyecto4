package com.example.user_service_api.Exception;

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
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("User not found: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.NOT_FOUND.value()
        );
        errorResponse.put("userId", ex.getUserId());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<Map<String, Object>> handleUserUpdateException(UserUpdateException ex) {
        log.error("User update failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        errorResponse.put("userId", ex.getUserId());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserDeletionException.class)
    public ResponseEntity<Map<String, Object>> handleUserDeletionException(UserDeletionException ex) {
        log.error("User deletion failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        errorResponse.put("userId", ex.getUserId());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleUserAuthenticationException(UserAuthenticationException ex) {
        log.error("Authentication failed: {}", ex.getMessage());

        Map<String, Object> errorResponse = createErrorResponse(
                "Authentication failed",
                ex.getErrorCode(),
                HttpStatus.UNAUTHORIZED.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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
