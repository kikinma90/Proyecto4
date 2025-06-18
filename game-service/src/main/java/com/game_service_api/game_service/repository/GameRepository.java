package com.game_service_api.game_service.repository;

import com.game_service_api.game_service.common.entities.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameModel, Long> {
}
