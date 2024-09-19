package com.radomir.drazic.birdwatchingapp.dto.response;

import java.util.Date;

public record ObservationDto(
    Double longitude,
    Double latitude,
    Date date,
    SpeciesPartialDto species
) {}