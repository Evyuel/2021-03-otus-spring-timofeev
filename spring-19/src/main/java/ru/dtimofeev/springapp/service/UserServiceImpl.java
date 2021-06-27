package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.User;
import ru.dtimofeev.springapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByLogin(String login){
        User u = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User with login: " + login + "not found."));
        return u;
    }
}
