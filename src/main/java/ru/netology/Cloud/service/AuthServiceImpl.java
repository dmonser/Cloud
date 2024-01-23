package ru.netology.Cloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.netology.Cloud.dtos.LoginRequest;
import ru.netology.Cloud.dtos.LoginResponse;
import ru.netology.Cloud.jwt.JwtGenerator;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password());
        final var authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var token = jwtGenerator.generateToken(authentication);
        return new LoginResponse(token);
    }

    @Override
    public void logout(String authToken) {
        // Реализация выхода, деактивация токена
        // ...
    }
}
