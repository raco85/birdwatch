package com.radomir.drazic.birdwatchingapp.dto.request;

public record CreateGenusRequestDto(
    String name,
    String latinName,
    Long familyId
) {}