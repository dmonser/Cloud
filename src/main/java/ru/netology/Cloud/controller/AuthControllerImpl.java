package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.Cloud.dtos.LoginRequest;
import ru.netology.Cloud.dtos.LoginResponse;
import ru.netology.Cloud.service.AuthService;

@RequestMapping("/cloud")
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        final var response = authService.login(loginRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("auth-token") String authToken) {
        authService.logout(authToken);
        return ResponseEntity.ok().build();
    }
}
