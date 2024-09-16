package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IOrderService {
  OrderDto createOrder(CreateOrderRequestDto orderDto);
  List<OrderDto> getAllOrders();
  OrderDto getOrderById(Long orderId);
  List<OrderDto> getOrdersByName(String name);
  List<OrderDto> getOrdersByLatinName(String latinName);
  OrderDto updateOrder(Long id, CreateOrderRequestDto orderRequest);
  void deleteOrder(Long id);
}