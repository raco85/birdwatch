package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.dto.CreateUserRequestDto;
import com.radomir.drazic.birdwatchingapp.entity.User;
import com.radomir.drazic.birdwatchingapp.mapper.UserMapper;
import com.radomir.drazic.birdwatchingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository repository;
  private final UserMapper mapper;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Override
  public String register(CreateUserRequestDto userRequest) {
    User userToSave = mapper.toEntityFromCreateUserRequestDto(userRequest);
    userToSave.setPassword(encoder.encode(userRequest.password()));
    User savedUser = repository.save(userToSave);
    User userToCheck = repository.findById(savedUser.getId()).orElseThrow();
    return userToCheck.equals(savedUser) ? "User with username " + savedUser.getUsername()
        + " is registered" : "User with username " + savedUser.getUsername() + " is not registered";
  }
}