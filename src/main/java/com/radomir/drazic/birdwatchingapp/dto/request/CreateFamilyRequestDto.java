package com.radomir.drazic.birdwatchingapp.dto.request;

public record CreateFamilyRequestDto(
    String name,
    String latinName,
    Long orderId
) {}