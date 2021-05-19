package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Genre;

public interface GenreService {
    Genre getById(long id);

    Genre getByName(String name);

    void insert(String name);
}
