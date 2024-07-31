package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateGenusRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.GenusDto;
import com.radomir.drazic.birdwatchingapp.entity.Genus;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collection.class})
public interface GenusMapper {
  GenusDto toGenesesDto(Genus genus);

  @Mapping(target = "genusId", ignore = true)
  @Mapping(target = "species", ignore = true)
  Genus toEntityFromCreateGenusDto(CreateGenusRequestDto genusRequestDto);
}