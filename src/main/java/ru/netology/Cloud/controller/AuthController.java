package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.Cloud.dto.LoginRequest;
import ru.netology.Cloud.dto.LoginResponse;
import ru.netology.Cloud.service.AuthServiceImpl;

@RequestMapping
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        final var response = authService.login(loginRequest);
        log.info(String.format("User '%s' success login", loginRequest.login()));
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("auth-token") String authToken) {
        authService.logout(authToken);
        log.info("User success logout");
        return ResponseEntity.ok().build();
    }
}
