package com.radomir.drazic.birdwatchingapp.controller;

import com.radomir.drazic.birdwatchingapp.dto.CreateUserRequestDto;
import com.radomir.drazic.birdwatchingapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService service;
  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody CreateUserRequestDto user){
    String result = service.register(user);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}