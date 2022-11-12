package com.example.fullregistrationsystem.services;

import com.example.fullregistrationsystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String .format(USER_NOT_FOUND_MSG, email)));
  }
}