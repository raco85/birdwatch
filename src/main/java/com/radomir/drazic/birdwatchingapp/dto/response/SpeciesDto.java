package com.radomir.drazic.birdwatchingapp.dto.response;

public record SpeciesDto(
    String name,
    String latinName,
    GenusPartialDTO genus
) {}