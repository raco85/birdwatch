package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  User register(User user);
}