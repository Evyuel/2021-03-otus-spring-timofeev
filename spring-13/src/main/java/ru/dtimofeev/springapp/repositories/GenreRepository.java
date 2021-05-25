package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dtimofeev.springapp.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    Genre save(Genre genre);

    Optional<Genre> findByName(String name);

    @Override
    List<Genre> findAll();
}
