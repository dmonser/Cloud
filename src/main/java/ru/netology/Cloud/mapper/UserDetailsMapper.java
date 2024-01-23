package ru.netology.Cloud.mapper;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.netology.Cloud.entities.User;
import ru.netology.Cloud.entities.Role;

@Component
public class UserDetailsMapper {

    public UserDetails mapUserToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new).toList()
        );
    }
}
