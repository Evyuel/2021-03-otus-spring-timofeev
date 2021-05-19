package ru.dtimofeev.spring.service.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.spring.dao.GenreJdbc;
import ru.dtimofeev.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс GenreServiceImpl должен")
@JdbcTest
@Import({GenreJdbc.class, GenreServiceImpl.class})
class GenreServiceImplTest {

    private static final String GENRE_NAME = "TestGenreName";
    @Autowired
    private GenreJdbc genreJdbc;
    @Autowired
    private GenreServiceImpl genreService;

    @DisplayName("корректно добавлять жанр по имени")
    @Test
    void shouldCorrectInsertBook() {
        int genreID = genreJdbc.getNextSequenceVal() + 1;
        genreService.insert(GENRE_NAME);
        Genre genre = new Genre(genreID, GENRE_NAME);
        assertThat(genre)
                .usingRecursiveComparison()
                .isEqualTo(genreJdbc.getById(genreID));
    }

}