package com.example.eligibility_microservice.services;

import com.example.eligibility_microservice.common.GameCreatedEvent;
import com.example.eligibility_microservice.common.GameEligibleEvent;
import reactor.core.publisher.Mono;

public interface GameEligibleService {
    Mono<GameEligibleEvent> eligibilityGame(GameCreatedEvent gameCreatedEvent);
}
