package com.radomir.drazic.birdwatchingapp.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderDto(
    String name,
    String latinName,
    List<FamilyPartialDTO> families
) {}