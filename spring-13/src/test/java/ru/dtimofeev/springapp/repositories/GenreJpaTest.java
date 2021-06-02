package ru.dtimofeev.springapp.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.dtimofeev.springapp.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс GenreJpa должен")
@DataJpaTest
class GenreJpaTest {

    private static final Genre GENRE_FOR_SAVE = new Genre(0, "NewGenre");

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" корректно сохранять жанр")
    @Test
    void shouldCorrectSaveGenre() {
        Genre genre = genreRepository.save(GENRE_FOR_SAVE);
        assertThat(genre.getId()).isGreaterThan(0);
        assertThat(genre)
                .usingRecursiveComparison()
                .isEqualTo(genreRepository.findById(genre.getId()).get());
    }
}