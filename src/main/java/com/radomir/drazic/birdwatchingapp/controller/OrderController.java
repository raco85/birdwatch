package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.CreateOrderRequestDto;
import com.radomir.drazic.birdwatchingapp.dto.response.OrderDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final IOrderService service;

  @GetMapping
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    return ResponseEntity.status(HttpStatus.OK).body(service.getAllOrders());
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequestDto orderRequest){
    OrderDto orderToSave = service.createOrder(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderToSave);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
    OrderDto order = service.getOrderById(id);
    return ResponseEntity.status(HttpStatus.OK).body(order);
  }

  @GetMapping("/name")
  public ResponseEntity<List<OrderDto>> getOrdersByName(@RequestParam String name) {
    List<OrderDto> orders = service.getOrdersByName(name);

    return ResponseEntity.status(HttpStatus.OK).body(orders);
  }

  @GetMapping("/latinName")
  public ResponseEntity<List<OrderDto>> getOrdersByLatinName(@RequestParam String latinName) {
    List<OrderDto> orders = service.getOrdersByLatinName(latinName);

    return ResponseEntity.status(HttpStatus.OK).body(orders);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody CreateOrderRequestDto orderRequest) {
    OrderDto orderToSave = service.updateOrder(id, orderRequest);
    return ResponseEntity.status(HttpStatus.OK).body(orderToSave);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
    service.deleteOrder(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}