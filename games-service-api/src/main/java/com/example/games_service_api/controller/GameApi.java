package com.example.games_service_api.controller;

import com.example.games_service_api.commons.constants.ApiPathConstants;
import com.example.games_service_api.commons.entities.GameModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public interface GameApi {

    @PostMapping(value = "create")
    ResponseEntity<GameModel> createGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @RequestBody GameModel gameRequest
    );

    @GetMapping(value = "/{gameId}")
    ResponseEntity<GameModel> getGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @PathVariable Long gameId
    );

    @PutMapping(value = "/{gameId}")
    ResponseEntity<GameModel> updateGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @RequestBody GameModel gameRequest,
            @PathVariable Long gameId
    );

    @DeleteMapping(value = "/{gameId}")
    ResponseEntity<GameModel> deleteGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @PathVariable Long gameId
    );
}
