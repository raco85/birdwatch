package com.radomir.drazic.birdwatchingapp.dto.response;

import java.util.Date;
import java.util.List;

public record ObservationDto(
    Double longitude,
    Double latitude,
    Date date,
    List<IndividualBirdDto> observedBirds
) {}