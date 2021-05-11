package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.dtimofeev.spring.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Класс GenreJdbc должен ")
@JdbcTest
@Import({GenreJdbc.class})
class GenreJdbcTest {

    private static final int EXPECTED_SEQUENCE_INCREASE = 4;
    private static final int GENRE_FOR_INSERT_ID = 10;
    private static final String GENRE_FOR_INSERT_NAME = "GenreForInsert";
    private static final Genre GENRE_FOR_INSERT = new Genre(GENRE_FOR_INSERT_ID,GENRE_FOR_INSERT_NAME);
    private static final int FIRST_GENRE_IN_DB_ID = 1;
    private static final String FIRST_GENRE_IN_DB_NAME = "Классика";
    private static final Genre FIRST_GENRE_IN_DB = new Genre(FIRST_GENRE_IN_DB_ID,FIRST_GENRE_IN_DB_NAME);
    private static final String FIRST_GENRE_IN_DB_NAME_UPDATED = "Классика_UpdatedName";
    private static final Genre FIRST_GENRE_IN_DB_UPDATED = new Genre(FIRST_GENRE_IN_DB_ID,FIRST_GENRE_IN_DB_NAME_UPDATED);
    private static final List<Genre> ALL_GENRES_IN_DB = new ArrayList<>(Arrays.asList(new Genre[]{
            new Genre(1,"Классика"),
            new Genre(2,"Детектив")
    }));

    @Autowired
    private GenreJdbc genreJdbc;
    @Autowired
    private JdbcTemplate jdbc;

    @DisplayName("корректно переключать и возвращать значение счетчика")
    @Test
    void shouldReturnCorrectSequenceValue() {
        int seqCurVal = genreJdbc.getNextSequenceVal();
        genreJdbc.getNextSequenceVal();
        genreJdbc.getNextSequenceVal();
        genreJdbc.getNextSequenceVal();
        assertEquals(seqCurVal+EXPECTED_SEQUENCE_INCREASE,jdbc.queryForObject("select genre_sq.nextval from dual",Integer.class));
    }

    @DisplayName("корректно возвращать жанр по ID")
    @Test
    void shouldCorrectReturnGenreById() {
        assertThat(FIRST_GENRE_IN_DB).usingRecursiveComparison().isEqualTo(genreJdbc.getById(FIRST_GENRE_IN_DB_ID));
    }

    @DisplayName("корректно возвращать жанр по Name")
    @Test
    void shouldCorrectReturnGenreByName() {
        assertThat(FIRST_GENRE_IN_DB).usingRecursiveComparison().isEqualTo(genreJdbc.getByName(FIRST_GENRE_IN_DB_NAME));
    }

    @DisplayName("корректно возвращать все жанры")
    @Test
    void shouldCorrectReturnAllGenre() {
        assertThat(ALL_GENRES_IN_DB)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(genreJdbc.getAll());
    }

    @DisplayName("корректно инсертить жанр")
    @Test
    void shouldCorrectInsert() {
        genreJdbc.insert(GENRE_FOR_INSERT);
        assertThat(GENRE_FOR_INSERT).usingRecursiveComparison().isEqualTo(genreJdbc.getById(GENRE_FOR_INSERT_ID));
    }

    @DisplayName("корректно апдейтить жанр")
    @Test
    void updateById() {
        genreJdbc.updateById(FIRST_GENRE_IN_DB_UPDATED);
        assertThat(FIRST_GENRE_IN_DB_UPDATED).usingRecursiveComparison().isEqualTo(genreJdbc.getById(FIRST_GENRE_IN_DB_ID));
    }

    @DisplayName("корректно удалять жанр")
    @Test
    void deleteById() {
        genreJdbc.insert(GENRE_FOR_INSERT);
        assertThatCode(() -> genreJdbc.getById(GENRE_FOR_INSERT_ID)).doesNotThrowAnyException();
        genreJdbc.deleteById(GENRE_FOR_INSERT_ID);
        assertThatThrownBy(() -> genreJdbc.getById(GENRE_FOR_INSERT_ID)).isExactlyInstanceOf(EmptyResultDataAccessException.class);
    }
}