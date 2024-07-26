package com.radomir.drazic.birdwatchingapp.dto.response;

public record OrderToSaveDto(
    Long orderId,
    String name,
    String latinName
) {}
