package com.radomir.drazic.birdwatchingapp.dto.request;

public record ObservationRadiusFilterRequestDto(
        Long distance,
        Double latitude,
        Double longitude
) {
}
