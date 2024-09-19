package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateObservationRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.ObservationDto;
import com.radomir.drazic.birdwatchingapp.entity.Observation;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collection.class}, uses = {SpeciesMapper.class})
public interface ObservationMapper {
  ObservationDto toObservationDto(Observation observation);

  @Mapping(target = "observationId", ignore = true)
  @Mapping(target = "observer.id", source = "observerId")
  @Mapping(target = "species.speciesId", source = "speciesId")
  Observation toEntityFromCreateObservationRequestDto(CreateObservationRequestDto observationRequestDto);
}