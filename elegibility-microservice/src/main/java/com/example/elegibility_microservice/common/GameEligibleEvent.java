package com.example.elegibility_microservice.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GameEligibleEvent {
    private Integer gameId;
    private String nameGame;
    private Integer userId;
    private Boolean isEligible;
}
