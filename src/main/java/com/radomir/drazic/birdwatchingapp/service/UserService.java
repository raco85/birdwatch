package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.request.CreateUserRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  String register(CreateUserRequestDto user);
}