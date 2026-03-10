package com.radomir.drazic.birdwatchingapp.dto.request;

import java.util.Date;

public record ObservationDateRequestDto(
        Date startingDate,
        Date endingDate
) {}