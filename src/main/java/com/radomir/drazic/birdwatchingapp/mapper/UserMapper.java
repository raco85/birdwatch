package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateUserRequestDto;
import com.radomir.drazic.birdwatchingapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "observations", ignore = true)
  User toEntityFromCreateUserRequestDto(CreateUserRequestDto userRequestDto);
}