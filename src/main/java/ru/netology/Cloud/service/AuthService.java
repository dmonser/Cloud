package ru.netology.Cloud.service;

import ru.netology.Cloud.dtos.LoginRequest;
import ru.netology.Cloud.dtos.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    void logout(String authToken);
}
