package ru.netology.Cloud.service;

import ru.netology.Cloud.entities.User;

public interface UserService {
    User getByLogin(String username);
}
