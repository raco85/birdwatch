package com.radomir.drazic.birdwatchingapp.dto;

import jakarta.validation.constraints.PastOrPresent;
import java.util.Date;

public record CreateObservationRequestDto(
    Double longitude,
    Double latitude,
    @PastOrPresent
    Date date,
    Long observerId,
    Long speciesId
) {}