package ru.netology.Cloud.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(@JsonProperty("auth-token") String authToken) {
}
