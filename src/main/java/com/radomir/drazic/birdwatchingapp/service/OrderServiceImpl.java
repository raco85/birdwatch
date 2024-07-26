package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderToSaveDto;
import com.radomir.drazic.birdwatchingapp.entity.Order;
import com.radomir.drazic.birdwatchingapp.mapper.OrderMapper;
import com.radomir.drazic.birdwatchingapp.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService{
  private final OrderRepository repository;
  private final OrderMapper mapper;

  @Override
  public OrderToSaveDto createOrder(CreateOrderRequestDto orderRequestDto) {
    Order orderToSave = mapper.toEntityFromCreateOrderRequestDto(orderRequestDto);
    Order savedOrder = repository.save(orderToSave);
    return mapper.toOrderToSaveDto(savedOrder);
  }

  @Override
  public List<OrderToSaveDto> getAllOrders() {
    List<Order> orders = repository.findAll();
    return orders.stream().map(mapper::toOrderToSaveDto).collect(
        Collectors.toList());
  }

  @Override
  public OrderToSaveDto getOrderById(Long orderId) {
    Order order = repository.findById(orderId).orElseThrow();
    return mapper.toOrderToSaveDto(order);
  }

  @Override
  public OrderToSaveDto updateOrder(Long id, CreateOrderRequestDto orderRequest) {
    Order order = repository.findById(id).orElseThrow();
    order.setName(orderRequest.name());
    order.setLatinName(orderRequest.latinName());
    Order updatedOrder = repository.save(order);
    return mapper.toOrderToSaveDto(updatedOrder);
  }

  @Override
  public void deleteOrder(Long id) {
    repository.deleteById(id);
  }
}