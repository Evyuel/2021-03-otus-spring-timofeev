package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);

    List<Author> findAll();

    Author save(Author author);

    void updateById(Author author);

    void deleteById(long id);
}
