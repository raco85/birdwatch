package com.radomir.drazic.birdwatchingapp.mapper;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderDto;
import com.radomir.drazic.birdwatchingapp.entity.Order;
import java.util.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Collections.class}, uses = {FamilyMapper.class})
public interface OrderMapper {
  @Mapping(target = "orderId", ignore = true)
  @Mapping(target = "families", ignore = true)
  Order toEntityFromCreateOrderRequestDto(CreateOrderRequestDto orderRequestDto);
  OrderDto toOrderDto(Order order);
}