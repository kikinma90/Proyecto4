package com.example.elegibility_microservice.services;

import com.example.elegibility_microservice.common.GameCreatedEvent;
import com.example.elegibility_microservice.common.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface GameEligibleService {
    Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent);
}
