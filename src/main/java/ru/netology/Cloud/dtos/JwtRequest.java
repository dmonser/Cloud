package ru.netology.Cloud.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
