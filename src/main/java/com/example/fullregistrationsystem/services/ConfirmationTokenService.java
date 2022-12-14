package com.example.fullregistrationsystem.services;

import com.example.fullregistrationsystem.models.ConfirmationToken;
import com.example.fullregistrationsystem.repositories.ConfirmationTokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationToken> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public int setConfirmedAt(String token) {
    return confirmationTokenRepository.updateConfirmedAt(
        token, LocalDateTime.now());
  }
}
