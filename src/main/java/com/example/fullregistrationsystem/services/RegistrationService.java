package com.example.fullregistrationsystem.services;

import com.example.fullregistrationsystem.config.RegistrationRequest;
import com.example.fullregistrationsystem.enums.UserRole;
import com.example.fullregistrationsystem.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final EmailValidator emailValidator;
  private final UserService userService;

  public String register(RegistrationRequest request) {
    boolean isEmailValid = emailValidator.test(request.getEmail());
    if (!isEmailValid) {
      throw new IllegalStateException("Email not valid");
    }

    return userService.signUpUser(new User(request.getFirstName(),
        request.getLastName(),
        request.getEmail(),
        request.getPassword(),
        UserRole.USER));
  }
}
