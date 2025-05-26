package com.example.user_service_api.service.impl;

import com.example.user_service_api.Exception.UserAuthenticationException;
import com.example.user_service_api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            if (username == null || username.trim().isEmpty()) {
                log.warn("Authentication attempt with null or empty username");
                throw new UserAuthenticationException(
                        "Username cannot be null or empty",
                        "INVALID_USERNAME",
                        username
                );
            }

            return userRepository.findByEmail(username)
                    .orElseThrow(() -> {
                        log.warn("User not found during authentication: {}", username);
                        throw new UserAuthenticationException(
                                "User not found with email: " + username,
                                "USER_NOT_FOUND_AUTH",
                                username
                        );
                    });
        } catch (UserAuthenticationException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during user authentication for username {}: {}", username, e.getMessage(), e);
            throw new UserAuthenticationException(
                    "Authentication service temporarily unavailable",
                    "AUTH_SERVICE_ERROR",
                    username,
                    e
            );
        }

    }
}
