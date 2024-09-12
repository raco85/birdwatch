package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.entity.User;
import com.radomir.drazic.birdwatchingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  UserRepository repository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Override
  public User register(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    repository.save(user);
    return user;
  }
}