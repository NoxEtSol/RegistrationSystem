package com.example.fullregistrationsystem.services;

import com.example.fullregistrationsystem.models.ConfirmationToken;
import com.example.fullregistrationsystem.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveCOnfirmationToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }
}
