package com.game_service_api.game_service.controller;

import com.game_service_api.game_service.common.constants.ApiPathConstants;
import com.game_service_api.game_service.common.dtos.CreateGame;
import com.game_service_api.game_service.common.dtos.UpdateGame;
import com.game_service_api.game_service.common.entities.GameModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTER + ApiPathConstants.GAME_ROUTER)
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public interface GameController {

    @PostMapping(value = "/create")
    ResponseEntity<GameModel> createGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @RequestBody CreateGame createGame);

    @GetMapping(value = "/{gameId}")
    ResponseEntity<GameModel> getGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @PathVariable Long gameId);

    @PutMapping(value = "/{gameId}")
    ResponseEntity<Void> updateGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @RequestBody UpdateGame updateGame,
            @PathVariable Long gameId);

    @DeleteMapping(value = "/{gameId}")
    ResponseEntity<Void> deleteGame(
            @RequestHeader("userIdRequest") String userId,
            @Valid @PathVariable Long gameId);
}
