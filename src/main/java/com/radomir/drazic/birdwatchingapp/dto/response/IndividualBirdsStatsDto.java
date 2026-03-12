package com.radomir.drazic.birdwatchingapp.dto.response;

public record IndividualBirdsStatsDto(
   Integer numberOfBirdsInARadius,
   Integer numberOfBirdsFoundByFilter,
   Double percentage
) {}