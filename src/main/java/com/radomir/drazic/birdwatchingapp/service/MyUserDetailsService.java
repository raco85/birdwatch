package com.radomir.drazic.birdwatchingapp.service;

import com.radomir.drazic.birdwatchingapp.entity.MyUserDetails;
import com.radomir.drazic.birdwatchingapp.entity.User;
import com.radomir.drazic.birdwatchingapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository repository;
  private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername(username);
    if(user == null) {
      logger.info("User with username {} not found!", username);
      throw new UsernameNotFoundException("User with username "+ username + " not found");
    }
    return new MyUserDetails(user);
  }
}