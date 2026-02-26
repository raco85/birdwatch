package com.radomir.drazic.birdwatchingapp.dto.response;

public record IndividualBirdDto(
        String ageClass,
        String gender,
        String breedingStatus,
        String behaviour,
        String habitat,
        String socialContext,
        String migratoryStatus,
        String physicalCondition,
        SpeciesPartialDto species
) {}