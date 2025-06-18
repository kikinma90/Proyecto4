package com.game_service_api.game_service.service;

import com.game_service_api.game_service.common.dtos.CreateGame;
import com.game_service_api.game_service.common.dtos.UpdateGame;
import com.game_service_api.game_service.common.entities.GameModel;

public interface  GameService {
    GameModel createGame(String udserId,CreateGame createGame);
    GameModel getGame(String udserId,Long gameId);
    void updateGame(String udserId,UpdateGame updateGame, Long gameId);
    void deleteGame(String udserId,Long gameId);
}
