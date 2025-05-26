package com.example.user_service_api.Exception;

import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class UserAuthenticationException extends UsernameNotFoundException {
    private final String errorCode;
    private final String username;

    public UserAuthenticationException(String message, String errorCode, String username) {
        super(message);
        this.errorCode = errorCode;
        this.username = username;
    }

    public UserAuthenticationException(String message, String errorCode, String username, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.username = username;
    }
}
