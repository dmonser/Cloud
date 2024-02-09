package ru.netology.Cloud.service;

import ru.netology.Cloud.dto.LoginRequest;
import ru.netology.Cloud.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    void logout(String authToken);
}
