package ru.dtimofeev.springapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.service.UserService;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ru.dtimofeev.springapp.models.User u = userService.getByLogin(login);
        return User.builder()
                .username(u.getLogin())
                .password(u.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(u.getUserAuthorities().stream().map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority())).collect(Collectors.toList()))
                .build();
    }
}
