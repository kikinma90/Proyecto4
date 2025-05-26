package com.example.elegibility_microservice.services.impl;

import com.example.elegibility_microservice.common.GameCreatedEvent;
import com.example.elegibility_microservice.common.GameEligibleEvent;
import com.example.elegibility_microservice.services.GameEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameEligibleServiceImpl implements GameEligibleService {
    @Override
    public Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .flatMap(this::checkIsEligle)
                .map(givenCreated -> GameEligibleEvent.builder()
                        .gameId(givenCreated.getGameId())
                        .nameGame(givenCreated.getNameGame())
                        .userId(givenCreated.getUserId())
                        .build());
    }

    private Mono<GameCreatedEvent> checkIsEligle(GameCreatedEvent gameCreatedEvent) {
        return Mono.just(gameCreatedEvent)
                .filter(given -> !given.getNameGame().isBlank())
                .map(given -> gameCreatedEvent);
    }
}
