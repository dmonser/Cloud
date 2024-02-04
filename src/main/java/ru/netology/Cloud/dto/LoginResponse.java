package ru.netology.Cloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(@JsonProperty("auth-token") String authToken) {
}
