package com.radomir.drazic.birdwatchingapp.dto;

public record CreateSpeciesRequestDto(
    String name,
    String latinName
) {}