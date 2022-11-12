package com.example.fullregistrationsystem.services;

import com.example.fullregistrationsystem.models.ConfirmationToken;
import com.example.fullregistrationsystem.models.User;
import com.example.fullregistrationsystem.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ConfirmationTokenService confirmationTokenService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String .format(USER_NOT_FOUND_MSG, email)));
  }

  public String signUpUser(User user) {
    boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("Email already taken");
    }
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

    user.setPassword(encodedPassword);

    userRepository.save(user);

    String token = UUID.randomUUID().toString();
    // TODO: Send confirmation token
    ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);

    confirmationTokenService.saveConfirmationToken(confirmationToken);
    // TODO: Send email
    return token;
  }

  public int enableUser(String email) {
    return userRepository.enableUser(email);
  }
}
