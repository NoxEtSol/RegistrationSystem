package com.example.fullregistrationsystem.controllers;

import com.example.fullregistrationsystem.config.RegistrationRequest;
import com.example.fullregistrationsystem.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

  private RegistrationService registrationService;

  @PostMapping
  public String register(@RequestBody RegistrationRequest request){
    return registrationService.register(request);
  }
}
