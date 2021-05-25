package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dtimofeev.springapp.models.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    Author save(Author author);

    Optional<Author> findByFullName(String fullName);

}
