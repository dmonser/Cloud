package ru.netology.Cloud.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.netology.Cloud.entities.User;
import ru.netology.Cloud.repositories.RoleRepository;
import ru.netology.Cloud.repositories.UserRepository;
import ru.netology.Cloud.service.UserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Authentication {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public boolean authenticate(String login, String password) {
        if (login.isEmpty() && password.isEmpty()) {
            return false;
        }


        User user = userService.findByUsername(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));

        return user.getPassword().equals(password);
    }
}
