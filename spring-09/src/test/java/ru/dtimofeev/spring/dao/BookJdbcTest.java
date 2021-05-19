package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.dtimofeev.spring.domain.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@DisplayName("Класс BookJdbc должен ")
@Import(BookJdbc.class)
class BookJdbcTest {

    private static final int EXPECTED_SEQUENCE_INCREASE = 4;
    private static final int BOOK_ID_FOR_INSERT = 90;
    private static final int BOOK_ID_FOR_UPDATE = 1;
    private static final Book BOOK_FOR_INSERT = new Book(BOOK_ID_FOR_INSERT, "BookWithID90", 1);
    private static final Book BOOK_FOR_UPDATE = new Book(BOOK_ID_FOR_UPDATE, "UpdatedBook", 1);
    private static final int FIRST_BOOK_IN_DB_ID = 1;
    private static final String FIRST_BOOK_IN_DB_NAME = "Мастер и Маргарита";
    private static final Book FIRST_BOOK_IN_DB = new Book(FIRST_BOOK_IN_DB_ID, "Мастер и Маргарита", 1);
    private static final List<Book> LIST_OF_BOOK_WITH_GENREID_1 = new ArrayList<>(Arrays.asList(new Book(1, "Мастер и Маргарита", 1),
            new Book(8, "Двенадцать стульев", 1)));
    private static final int GENRE_WITH_ID_1 = 1;
    private static final List<Book> LIST_OF_ALL_BOOKS = new ArrayList<>(Arrays.asList(new Book(1, "Мастер и Маргарита", 1),
            new Book(8, "Двенадцать стульев", 1),
            new Book(3, "Десять негритят", 2)));

    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private JdbcTemplate jdbc;

    @DisplayName("корректно переключать и возвращать значение счетчика")
    @Test
    void shouldReturnCorrectSequenceValue() {
        int seqCurVal = bookJdbc.getNextSequenceVal();
        bookJdbc.getNextSequenceVal();
        bookJdbc.getNextSequenceVal();
        bookJdbc.getNextSequenceVal();
        assertEquals(seqCurVal + EXPECTED_SEQUENCE_INCREASE, jdbc.queryForObject("select book_sq.nextval from dual", Integer.class));
    }

    @DisplayName("корректно корректно возвращать книгу по ID")
    @Test
    void shouldCorrectReturnBookById() {
        assertThat(FIRST_BOOK_IN_DB).usingRecursiveComparison().isEqualTo(bookJdbc.getById(FIRST_BOOK_IN_DB_ID));
    }

    @DisplayName("корректно добавлять книгу")
    @Test
    void shouldCorrectInsert() {
        bookJdbc.insert(BOOK_FOR_INSERT);
        assertThat(BOOK_FOR_INSERT).usingRecursiveComparison().isEqualTo(bookJdbc.getById(BOOK_ID_FOR_INSERT));
    }

    @DisplayName("корректно апдейтить книгу по ID")
    @Test
    void shouldCorrectUpdateById() {
        bookJdbc.updateById(BOOK_FOR_UPDATE);
        assertThat(BOOK_FOR_UPDATE).usingRecursiveComparison().isEqualTo(bookJdbc.getById(BOOK_ID_FOR_UPDATE));
    }

    @DisplayName("корректно удалять книгу по ID")
    @Test
    void shouldCorrectDeleteById() {
        bookJdbc.insert(BOOK_FOR_INSERT);
        assertThatCode(() -> bookJdbc.getById(BOOK_ID_FOR_INSERT)).doesNotThrowAnyException();

        bookJdbc.deleteById(BOOK_ID_FOR_INSERT);
        assertThatThrownBy(() -> bookJdbc.getById(BOOK_ID_FOR_INSERT)).isExactlyInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("корректно возвращать книги по жанру")
    @Test
    void shouldReturnCorrectListOfBooksByGenreID() {
        assertThat(LIST_OF_BOOK_WITH_GENREID_1)
                .usingFieldByFieldElementComparator()
                .isEqualTo(bookJdbc.getByGenreID(GENRE_WITH_ID_1));
    }

    @DisplayName("корректно возвращать книги по имени")
    @Test
    void shouldReturnCorrectListOfBooksByName() {
        assertThat(FIRST_BOOK_IN_DB)
                .usingRecursiveComparison()
                .isEqualTo(bookJdbc.getByName(FIRST_BOOK_IN_DB_NAME));
    }

    @DisplayName("корректно возвращать все книги")
    @Test
    void shouldReturnCorrectAllBooks() {
        assertThat(LIST_OF_ALL_BOOKS)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(bookJdbc.getAll());
    }
}