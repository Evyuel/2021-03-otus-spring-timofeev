package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.dtimofeev.spring.domain.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
@DisplayName("Класс AuthorJdbc должен ")
@Import(AuthorJdbc.class)
class AuthorJdbcTest {

    public static final int EXPECTED_SEQUENCE_INCREASE = 4;
    public static final int AUTHOR_ID = 40;
    public static final String AUTHOR_NAME = "AuthorTestName";
    public static final int AUTHOR_WITH_ID_1 = 1;
    public static final String AUTHOR_FOR_UPDATE_NAME = "UpdatedAuthor";
    public static final List<Author> INITIAL_LIST_OF_AUTHORS = new ArrayList<>(Arrays.asList(
                                                                    new Author[]{new Author(1, "Михаил Булгаков"),
                                                                                 new Author(3, "Агата Кристи"),
                                                                                 new Author(9, "Илья Ильф"),
                                                                                 new Author(10, "Евгений Петров")}
                                                                                           )
                                                                             );
    @Autowired
    private AuthorJdbc authorJdbc;

    @DisplayName("корректно переключать и возвращать значение счетчика")
    @Test
    void shouldReturnCorrectSequenceValue() {
        int seqCurVal = authorJdbc.getNextSequenceVal();
        authorJdbc.getNextSequenceVal();
        authorJdbc.getNextSequenceVal();
        authorJdbc.getNextSequenceVal();
        assertEquals(seqCurVal+EXPECTED_SEQUENCE_INCREASE,authorJdbc.getNextSequenceVal());
    }

    @DisplayName("корректно выбирать автора по ID")
    @Test
    void shouldCorrectReturnAuthor() {
        assertThat(authorJdbc.getById(AUTHOR_WITH_ID_1)).usingRecursiveComparison().isEqualTo(new Author(1,"Михаил Булгаков"));
    }

    @DisplayName("корректно выбирать автора по полному имени")
    @Test
    void getByFullName() {
        assertThat(authorJdbc.getByFullName("Михаил Булгаков")).usingRecursiveComparison().isEqualTo(new Author(1,"Михаил Булгаков"));
    }

    @DisplayName("корректно выбирать всех авторов")
    @Test
    void getAll() {
        List<Author> actualList = authorJdbc.getAll();
        assertThat(actualList)
                .usingFieldByFieldElementComparator()
                .isEqualTo(INITIAL_LIST_OF_AUTHORS);
    }

    @DisplayName("корректно добавлять автора")
    @Test
    void shouldCorrectInsertAuthor() {
        Author author = new Author(AUTHOR_ID, AUTHOR_NAME);
        authorJdbc.insert(author);
        assertThat(author).usingRecursiveComparison().isEqualTo(authorJdbc.getById(AUTHOR_ID));
    }

    @DisplayName("корректно апдейтить автора")
    @Test
    void shouldCorrectUpdateAuthorByID() {
        Author authorForUpdate = new Author(AUTHOR_WITH_ID_1, AUTHOR_FOR_UPDATE_NAME);
        authorJdbc.updateById(authorForUpdate);
        assertThat(authorForUpdate).usingRecursiveComparison().isEqualTo(authorJdbc.getById(AUTHOR_WITH_ID_1));
    }

    @DisplayName("корректно удалять автора")
    @Test
    void shouldCorrectDeleteAuthorByID() {
        Author author = new Author(AUTHOR_ID, AUTHOR_NAME);
        authorJdbc.insert(author);

        assertThatCode(() -> authorJdbc.getById(AUTHOR_ID)).doesNotThrowAnyException();
        authorJdbc.deleteById(author.getId());
        assertThatThrownBy(() -> authorJdbc.getById(AUTHOR_ID)).isExactlyInstanceOf(EmptyResultDataAccessException.class);

    }




}