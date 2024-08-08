package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderToSaveDto;
import com.radomir.drazic.birdwatchingapp.entity.Order;
import com.radomir.drazic.birdwatchingapp.exception.ResourceNotFoundException;
import com.radomir.drazic.birdwatchingapp.mapper.OrderMapper;
import com.radomir.drazic.birdwatchingapp.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
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
  public OrderToSaveDto getOrderById(Long id) {
    Order order = findOrderById(id);
    return mapper.toOrderToSaveDto(order);
  }

  @Override
  public OrderToSaveDto updateOrder(Long id, CreateOrderRequestDto orderRequest) {
    Order order = findOrderById(id);
    order.setName(orderRequest.name());
    order.setLatinName(orderRequest.latinName());
    Order updatedOrder = repository.save(order);
    return mapper.toOrderToSaveDto(updatedOrder);
  }

  @Override
  @Transactional
  public void deleteOrder(Long id) {
    findOrderById(id);
    repository.deleteById(id);
  }

  private Order findOrderById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> {
          logger.info("Order with an id {} not found!", id);
          return new ResourceNotFoundException("Order with id " + id + " not found!");
        }
    );
  }
}