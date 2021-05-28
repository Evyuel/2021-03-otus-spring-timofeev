package ru.dtimofeev.springapp.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс GenreJpa должен")
@DataJpaTest
class GenreJpaTest {

    private static final long FIRST_GENRE_ID = 1L;
    private static final Genre GENRE_FOR_SAVE = new Genre(0, "NewGenre");

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager testEntityManager;

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