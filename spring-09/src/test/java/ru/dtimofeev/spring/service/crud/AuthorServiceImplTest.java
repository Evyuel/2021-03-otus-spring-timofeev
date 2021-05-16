package ru.dtimofeev.spring.service.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.spring.dao.AuthorJdbc;
import ru.dtimofeev.spring.dao.BookAuthorLinkJdbc;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(properties =
//        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
//                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
//)
@DisplayName("Класс AuthorServiceImpl должен ")
@JdbcTest
@Import({AuthorJdbc.class, AuthorServiceImpl.class, BookAuthorLinkJdbc.class})
class AuthorServiceImplTest {

    private final static List<Author> LIST_OF_AUTHORS_WITH_SAME_BOOK_ID = new ArrayList<>(Arrays.asList(new Author(9, "Илья Ильф"),
            new Author(10, "Евгений Петров")));
    private static final int BOOK_ID = 8;
    private static final String AUTHOR_NAME = "AuthorTestName";

    @Autowired
    private AuthorJdbc authorJdbc;

    @Autowired
    private AuthorServiceImpl authorService;

    @DisplayName("Должен возвращать лист с Author по BookID")
    @Test
    void shouldReturnAuthorListByBookID() {
        assertThat(LIST_OF_AUTHORS_WITH_SAME_BOOK_ID)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(authorService.getAuthorListByBookID(BOOK_ID));
    }

    @DisplayName("корректно добавлять автора по имени")
    @Test
    void shouldCorrectInsertAuthor() {
        int authorID = authorJdbc.getNextSequenceVal() + 1;
        Author author = new Author(authorID, AUTHOR_NAME);
        authorService.insert(AUTHOR_NAME);
        assertThat(author).usingRecursiveComparison().isEqualTo(authorService.getById(authorID));
    }
}