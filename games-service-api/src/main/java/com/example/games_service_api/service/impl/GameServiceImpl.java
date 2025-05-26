package com.example.games_service_api.service.impl;

import com.example.games_service_api.commons.constants.TopicConstants;
import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.exception.GameCreationException;
import com.example.games_service_api.exception.GameDeletionException;
import com.example.games_service_api.exception.GameNotFoundException;
import com.example.games_service_api.exception.GameUpdateException;
import com.example.games_service_api.repository.GameRepository;
import com.example.games_service_api.service.GameService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final StreamBridge streamBridge;

    public GameServiceImpl(GameRepository gameRepository, StreamBridge streamBridge) {
        this.gameRepository = gameRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public GameModel createGame(GameModel gameRequest, String userId) {
        return Optional.of(gameRequest)
                .map(this::validateGameRequest)
                .map(this::mapToEntity)
                .map(entity -> {
                    entity.setUserId(userId);
                    return gameRepository.save(entity);
                })
                .map(this::sendGameEvent)
                .orElseThrow(() -> new GameCreationException(
                        "Failed to create game for user: " + userId,
                        "GAME_CREATION_FAILED"
                ));
    }

    private GameModel sendGameEvent(GameModel gameModel) {
      Optional.of(gameModel)
              .map(givenGame -> this.streamBridge.send(TopicConstants.GAME_CREATED_TOPIC, gameModel))
              .map(bool -> gameModel);

      return gameModel;
    };

    @Override
    public GameModel getGame(Long gameId, String userId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .filter(game -> userId.equals(game.getUserId()))
                .orElseThrow(() -> new GameNotFoundException(
                        "Game not found with ID: " + gameId,
                        "GAME_NOT_FOUND",
                        gameId
                ));
    }

    @Override
    public GameModel updateGame(Long gameId, GameModel gameRequest, String userId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .filter(game -> userId.equals(game.getUserId()))
                .map(existingGame -> {

                    existingGame.setName(gameRequest.getName());

                    return gameRepository.save(existingGame);
                })
                .orElseThrow(() -> new GameUpdateException(
                        "Failed to update game with ID: " + gameId + " for user: " + userId,
                        "GAME_UPDATE_FAILED",
                        gameId,
                        userId
                ));
    }

    @Override
    public GameModel deleteGame(Long gameId, String userId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .filter(game -> userId.equals(game.getUserId()))
                .map(game -> {
                    gameRepository.deleteById(gameId);
                    return game;
                })
                .orElseThrow(() -> new GameDeletionException(
                        "Failed to delete game with ID: " + gameId + " for user: " + userId,
                        "GAME_DELETION_FAILED",
                        gameId,
                        userId
                ));
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .build();
    }

    private GameModel validateGameRequest(GameModel gameRequest) {
        if (gameRequest == null) {
            throw new IllegalArgumentException("Game request cannot be null");
        }
        if (gameRequest.getName() == null || gameRequest.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Game name cannot be null or empty");
        }
        if (gameRequest.getName().length() > 100) {
            throw new IllegalArgumentException("Game name cannot exceed 100 characters");
        }
        return gameRequest;
    }
}
