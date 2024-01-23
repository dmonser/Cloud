package ru.netology.Cloud.controller;

import org.springframework.http.ResponseEntity;
import ru.netology.Cloud.dtos.LoginRequest;
import ru.netology.Cloud.dtos.LoginResponse;

public interface AuthController {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
    ResponseEntity<Void> logout(String authToken);
}
