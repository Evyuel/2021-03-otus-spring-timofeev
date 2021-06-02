package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> findById(long id);

    List<Author> findAll();

    Optional<Author> findByName(String fullName);

    Author save(Author author);
}
