package com.example.games_service_api.service;

import com.example.games_service_api.commons.entities.GameModel;

public interface GameService {
    GameModel createGame(GameModel gameRequest, String userId);

    GameModel getGame(Long gameId, String userId);

    GameModel updateGame(Long gameId, GameModel gameRequest, String userId);

    GameModel deleteGame(Long gameId, String userId);
}
