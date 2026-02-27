package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.request.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import com.radomir.drazic.birdwatchingapp.entity.Observation;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collection.class}, uses = {IndividualBirdMapper.class})
public interface ObservationMapper {
  @Mapping(target = "observedBirds", source = "birds")
  ObservationDto toObservationDto(Observation observation);

  @Mapping(target = "observationId", ignore = true)
  @Mapping(target = "observer.id", source = "observerId")
  Observation toEntityFromCreateObservationRequestDto(CreateObservationRequestDto observationRequestDto);
}