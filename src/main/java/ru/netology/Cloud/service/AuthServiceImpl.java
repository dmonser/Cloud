package ru.netology.Cloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.netology.Cloud.dto.LoginRequest;
import ru.netology.Cloud.dto.LoginResponse;
import ru.netology.Cloud.jwt.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userServiceImpl;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password()));
        } catch (HttpClientErrorException.Unauthorized exception) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad credentials");
        }
        UserDetails userDetails = userServiceImpl.loadUserByUsername(loginRequest.login());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new LoginResponse(token);
    }

    @Override
    public void logout(String authToken) {

    }
}
