package ru.dtimofeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.dtimofeev.spring.dao.AuthorJdbc;
import ru.dtimofeev.spring.dao.BookAuthorLinkJdbc;
import ru.dtimofeev.spring.dao.BookJdbc;
import ru.dtimofeev.spring.dao.GenreJdbc;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс BookProcessingServiceImpl должен")
@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)
class BookProcessingServiceImplTest {


    private static final String TEST_GENRE_NAME = "Тестовый жанр";
    private static final String TEST_AUTHOR_NAMES = "Илья Ильич,Серега Серегин,Вася Васин";
    private static final List<String> TEST_AUTHOR_NAMES_LIST = new ArrayList<>(Arrays.asList("Илья Ильич", "Серега Серегин", "Вася Васин"));
    private static final String TEST_BOOK_NAME = "Очень классная книга";

    @Autowired
    private BookProcessingServiceImpl bookProcessingService;
    @Autowired
    private AuthorJdbc authorJdbc;
    @Autowired
    private GenreJdbc genreJdbc;
    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private BookAuthorLinkJdbc bookAuthorLinkJdbc;

    @DisplayName("должен корректно добавлять книгу с зависимыми сущностями")
    @Test
    void shouldCorrectAddBookWithDependentEntities() {
        bookProcessingService.checkAndAddNewBook(TEST_GENRE_NAME, TEST_AUTHOR_NAMES, TEST_BOOK_NAME);

        Book book = bookJdbc.getByName(TEST_BOOK_NAME);
        assertEquals(TEST_GENRE_NAME, genreJdbc.getById(bookJdbc.getByName(TEST_BOOK_NAME).getGenreID()).getName());

        List<String> listOfAuthorsName = new ArrayList<>();
        for (BookAuthorLink bal : bookAuthorLinkJdbc.getBookAuthorLinksByBookID(book.getId())) {
            listOfAuthorsName.add(authorJdbc.getById(bal.getAuthorID()).getFullName());
        }
        assertThat(TEST_AUTHOR_NAMES_LIST).containsExactlyInAnyOrderElementsOf(listOfAuthorsName);
    }

    @DisplayName("должен добавлять книгу,если ее еще нет")
    @Test
    void shouldCorrectAddBookWithDependentEntitiesIfAbsent() {
        // bookProcessingService.checkAndAddNewBook();
    }

}