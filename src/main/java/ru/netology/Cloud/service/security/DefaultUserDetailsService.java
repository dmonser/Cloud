package ru.netology.Cloud.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.Cloud.mapper.UserDetailsMapper;
import ru.netology.Cloud.service.UserServiceImpl;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService /*implements UserDetailsService*/ {
//    private final UserServiceImpl userService;
//    private final UserDetailsMapper userDetailsMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final var user = userService.getByLogin(username);
//        return userDetailsMapper.mapUserToUserDetails(user);
//    }
}
