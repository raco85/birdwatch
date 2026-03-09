package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.request.IndividualBirdRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.IndividualBirdDto;
import com.radomir.drazic.birdwatchingapp.entity.IndividualBird;
import com.radomir.drazic.birdwatchingapp.entity.enums.LabeledEnum;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(componentModel = "spring", imports = {Collection.class}, uses = SpeciesMapper.class)
public interface IndividualBirdMapper {

    @Mapping(target = "ageClass", source = "ageClass", qualifiedByName = "toLabel")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "toLabel")
    @Mapping(target = "breedingStatus", source = "breedingStatus", qualifiedByName = "toLabel")
    @Mapping(target = "behaviour", source = "behaviour", qualifiedByName = "toLabel")
    @Mapping(target = "habitat", source = "habitat", qualifiedByName = "toLabel")
    @Mapping(target = "socialContext", source = "socialContext", qualifiedByName = "toLabel")
    @Mapping(target = "migratoryStatus", source = "migratoryStatus", qualifiedByName = "toLabel")
    @Mapping(target = "physicalCondition", source = "physicalCondition", qualifiedByName = "toLabel")
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

    @Mapping(target = "ageClass", source = "ageClass", qualifiedByName = "toUpperCase")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "toUpperCase")
    @Mapping(target = "breedingStatus", source = "breedingStatus", qualifiedByName = "toUpperCase")
    @Mapping(target = "behaviour", source = "behaviour", qualifiedByName = "toUpperCase")
    @Mapping(target = "habitat", source = "habitat", qualifiedByName = "toUpperCase")
    @Mapping(target = "socialContext", source = "socialContext", qualifiedByName = "toUpperCase")
    @Mapping(target = "migratoryStatus", source = "migratoryStatus", qualifiedByName = "toUpperCase")
    @Mapping(target = "physicalCondition", source = "physicalCondition", qualifiedByName = "toUpperCase")
    void updateEntityFromDto(IndividualBirdRequestDto dto, @MappingTarget IndividualBird entity);

    @Named("toUpperCase")
    default String toUpperCase(String value)
    {
        return value.replaceAll(" ", "_").toUpperCase();
    }

    @Named("toLabel")
    default String toLabel(LabeledEnum value)
    {
        return value.getLabel();
    }
}