package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateFamilyRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.FamilyDto;
import com.radomir.drazic.birdwatchingapp.entity.Family;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collection.class}, uses = {GenusMapper.class})
public interface FamilyMapper {
  FamilyDto toFamilyDto(Family family);
  @Mapping(target = "familyId", ignore = true)
  @Mapping(target = "geneses", ignore = true)
  @Mapping(target = "order.orderId", source = "orderId")
  Family toEntityFromCreateFamilyRequestDto(CreateFamilyRequestDto familyRequestDto);
}