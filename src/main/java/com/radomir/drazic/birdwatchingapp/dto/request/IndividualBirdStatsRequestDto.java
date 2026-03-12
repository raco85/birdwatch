package com.radomir.drazic.birdwatchingapp.dto.request;

import java.util.Date;

public record IndividualBirdStatsRequestDto(

        Long individualBirdId,
        String ageClass,
        String gender,
        String breedingStatus,
        String behaviour,
        String habitat,
        String socialContext,
        String migratoryStatus,
        String physicalCondition,
        Long speciesId,
        Long distance,
        Double latitude,
        Double longitude,
        Date startingDate,
        Date endingDate
) {}