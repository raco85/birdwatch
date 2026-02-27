package com.radomir.drazic.birdwatchingapp.dto.request;

public record CreateSpeciesRequestDto(
    String name,
    String latinName,
    Long genusId,
    String image
) {}