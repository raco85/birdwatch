package com.radomir.drazic.birdwatchingapp.dto;

public record IndividualBirdRequestDto(

        Long individualBirdId,
        String ageClass,
        String gender,
        String breedingStatus,
        String behaviour,
        String habitat,
        String socialContext,
        String migratoryStatus,
        String physicalCondition,
        Long speciesId
) {}