package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.models.User;

public interface UserService {
    User getByLogin(String login);
}
