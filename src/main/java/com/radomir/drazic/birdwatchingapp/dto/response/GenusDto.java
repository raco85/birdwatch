package com.radomir.drazic.birdwatchingapp.dto.response;

import java.util.List;

public record GenusDto(
    String name,
    String latinName,
    FamilyPartialDTO family,
    List<SpeciesPartialDto> species
) {}