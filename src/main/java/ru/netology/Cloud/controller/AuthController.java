package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.Cloud.dto.LoginRequest;
import ru.netology.Cloud.dto.LoginResponse;
import ru.netology.Cloud.exception.AppError;
import ru.netology.Cloud.jwt.JwtTokenUtils;
import ru.netology.Cloud.service.UserService;

import java.security.Principal;

//@RequestMapping("/cloud")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.login());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }


    // TODO: 30.01.2024 Logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        return ResponseEntity.ok().body("logout");
    }
}
