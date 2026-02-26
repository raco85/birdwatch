package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.IndividualBirdRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdDto;
import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(componentModel = "spring", imports = {Collection.class}, uses = SpeciesMapper.class)
public interface IndividualBirdMapper {

    IndividualBirdDto toIndividualBirdDto(IndividualBird bird);

    @Mapping(target = "individualBirdId", ignore = true)
    @Mapping(target = "ageClass", source = "ageClass", qualifiedByName = "toUpperCase")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "toUpperCase")
    @Mapping(target = "breedingStatus", source = "breedingStatus", qualifiedByName = "toUpperCase")
    @Mapping(target = "behaviour", source = "behaviour", qualifiedByName = "toUpperCase")
    @Mapping(target = "habitat", source = "habitat", qualifiedByName = "toUpperCase")
    @Mapping(target = "socialContext", source = "socialContext", qualifiedByName = "toUpperCase")
    @Mapping(target = "migratoryStatus", source = "migratoryStatus", qualifiedByName = "toUpperCase")
    @Mapping(target = "physicalCondition", source = "physicalCondition", qualifiedByName = "toUpperCase")
    @Mapping(target = "species.speciesId", source = "speciesId")
    IndividualBird toEntityFromCreateIndividualBird(IndividualBirdRequestDto dto);

    void updateEntityFromDto(IndividualBirdRequestDto dto, @MappingTarget IndividualBird entity);

    @Named("toUpperCase")
    default String toUpperCase(String value) {
        return value.toUpperCase();
    }
}