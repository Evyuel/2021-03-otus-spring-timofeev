package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findById(long id);

    List<Genre> findAll();

    Optional<Genre> findByName(String genreName);

    Genre save(Genre genre);

    void updateById(Genre genre);
}
