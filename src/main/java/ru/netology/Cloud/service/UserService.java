package ru.netology.Cloud.service;

import ru.netology.Cloud.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByLogin(String username);
}
