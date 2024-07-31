package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderToSaveDto;
import com.radomir.drazic.birdwatchingapp.service.IOrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final IOrderService service;

  @GetMapping
  public ResponseEntity<List<OrderToSaveDto>> getAllOrders() {
    return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrders());
  }

  @PostMapping
  public ResponseEntity<OrderToSaveDto> createOrder(@RequestBody CreateOrderRequestDto orderRequest){
    OrderToSaveDto orderToSave = service.createOrder(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderToSave);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderToSaveDto> getOrderById(@PathVariable Long id) {
    OrderToSaveDto order = service.getOrderById(id);
    return ResponseEntity.status(HttpStatus.OK).body(order);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderToSaveDto> updateOrder(@PathVariable Long id, @RequestBody CreateOrderRequestDto orderRequest) {
    OrderToSaveDto orderToSave = service.updateOrder(id, orderRequest);
    return ResponseEntity.status(HttpStatus.OK).body(orderToSave);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
    service.deleteOrder(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}