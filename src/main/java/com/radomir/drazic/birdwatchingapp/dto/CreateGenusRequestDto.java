package com.radomir.drazic.birdwatchingapp.dto;

public record CreateGenusRequestDto(
    String name,
    String latinName,
    Long familyId
) {}