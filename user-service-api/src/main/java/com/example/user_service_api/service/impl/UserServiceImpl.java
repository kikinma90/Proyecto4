package com.example.user_service_api.service.impl;

import com.example.user_service_api.Exception.UserDeletionException;
import com.example.user_service_api.Exception.UserNotFoundException;
import com.example.user_service_api.Exception.UserUpdateException;
import com.example.user_service_api.commons.entities.UserModel;
import com.example.user_service_api.repositories.UserRepository;
import com.example.user_service_api.service.UserService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUser(Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        return Optional.of(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new UserNotFoundException(
                        "Failed to retrieve user with ID: " + userId,
                        "USER_RETRIEVAL_FAILED",
                        userId
                ));
    }

    @Override
    public void updateUser(Long userId, UserModel userRequest) {

        try {
            if (userId == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }

            userRepository.findById(userId)
                    .map(existingUser -> {
                        if (userRequest.getEmail() != null) {
                            existingUser.setEmail(userRequest.getEmail());
                        }
                        if (userRequest.getName() != null) {
                            existingUser.setName(userRequest.getName());
                        }
                        if (userRequest.getRole() != null) {
                            existingUser.setRole(userRequest.getRole());
                        }
                        UserModel savedUser = userRepository.save(existingUser);
                        log.info("User with ID: {} successfully updated", userId);
                        return savedUser;
                    })
                    .orElseThrow(() -> new UserNotFoundException(
                            "Cannot update: User not found with ID: " + userId,
                            "USER_NOT_FOUND_FOR_UPDATE",
                            userId
                    ));
        } catch (Exception e) {
            log.error("Error updating user with ID {}: {}", userId, e.getMessage(), e);

            if (e instanceof UserNotFoundException || e instanceof IllegalArgumentException) {
                throw e;
            }

            throw new UserUpdateException(
                    "Unexpected error occurred while updating user with ID: " + userId,
                    "USER_UPDATE_UNEXPECTED_ERROR",
                    userId,
                    e
            );
        }

    }

    @Override
    public void deleteUser(Long userId) {

        try {
            if (userId == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }

            userRepository.findById(userId)
                    .ifPresent(userRepository::delete);
        } catch (Exception e) {
            log.error("Error deleting user with ID {}: {}", userId, e.getMessage(), e);

            if (e instanceof UserNotFoundException || e instanceof IllegalArgumentException) {
                throw e;
            }

            throw new UserDeletionException(
                    "Unexpected error occurred while deleting user with ID: " + userId,
                    "USER_DELETION_UNEXPECTED_ERROR",
                    userId,
                    e
            );
        }


    }

}
