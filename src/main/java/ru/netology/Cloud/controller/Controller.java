package ru.netology.Cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.Cloud.dtos.JwtRequest;
import ru.netology.Cloud.dtos.JwtResponse;
import ru.netology.Cloud.exceptions.AppError;
import ru.netology.Cloud.service.UserService;
import ru.netology.Cloud.utils.Authentication;
import ru.netology.Cloud.utils.JwtTokenUtils;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final Authentication authentication;

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello, user!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin!";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        System.out.println(authRequest);
        if (!authentication.authenticate(authRequest.getLogin(), authRequest.getPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
//        }
//
//        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
//        String token = jwtTokenUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
}
