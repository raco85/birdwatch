package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderToSaveDto;
import java.util.List;

public interface IOrderService {

  OrderToSaveDto createOrder(CreateOrderRequestDto orderDto);
  List<OrderToSaveDto> getAllOrders();
  OrderToSaveDto getOrderById(Long orderId);
  OrderToSaveDto updateOrder(Long id, CreateOrderRequestDto orderRequest);
  void deleteOrder(Long id);
}
