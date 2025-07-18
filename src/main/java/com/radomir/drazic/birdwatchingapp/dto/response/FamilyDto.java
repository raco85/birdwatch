package com.radomir.drazic.birdwatchingapp.dto.response;

import java.util.List;

public record FamilyDto(
    String name,
    String latinName,
    OrderPartialDTO order,
    List<GenusPartialDTO> geneses
) {}