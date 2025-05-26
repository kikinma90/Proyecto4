package com.example.games_service_api.controller.impl;

import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.controller.GameApi;
import com.example.games_service_api.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi{

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<GameModel> createGame(String userId, GameModel gameRequest) {
        return ResponseEntity.ok(gameService.createGame(gameRequest, userId));
    }

    @Override
    public ResponseEntity<GameModel> getGame(String userId, Long gameId) {

        return ResponseEntity.ok(gameService.getGame(gameId, userId));
    }

    @Override
    public ResponseEntity<GameModel> updateGame(String userId, GameModel gameRequest, Long gameId) {
        return ResponseEntity.ok(gameService.updateGame(gameId, gameRequest, userId));
    }

    @Override
    public ResponseEntity<GameModel> deleteGame(String userId, Long gameId) {
        return ResponseEntity.ok(gameService.deleteGame(gameId, userId));
    }
}
