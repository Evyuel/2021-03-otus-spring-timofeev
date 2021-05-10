package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);

    void updateById(Genre genre);

    void deleteById(long id);

    Genre getById(long id);

    Genre getByName(String name);

    int getNextSequenceVal();

    List<Genre> getAll();
}
