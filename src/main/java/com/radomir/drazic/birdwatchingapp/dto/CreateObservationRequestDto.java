package com.radomir.drazic.birdwatchingapp.dto;

import jakarta.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

public record CreateObservationRequestDto(
    Double longitude,
    Double latitude,
    @PastOrPresent
    Date date,
    List<IndividualBirdRequestDto> observedBirds,
    Long observerId
) {}