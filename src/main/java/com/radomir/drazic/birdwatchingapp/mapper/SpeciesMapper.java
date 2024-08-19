package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateSpeciesRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.SpeciesDto;
import com.radomir.drazic.birdwatchingapp.entity.Species;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collection.class})
public interface SpeciesMapper {
  SpeciesDto toSpeciesDto(Species species);
  @Mapping(target = "speciesId", ignore = true)
  @Mapping(target = "genus.genusId", source = "genusId")
  Species toEntityFromCreateSpeciesRequestDto(CreateSpeciesRequestDto speciesRequestDto);
}