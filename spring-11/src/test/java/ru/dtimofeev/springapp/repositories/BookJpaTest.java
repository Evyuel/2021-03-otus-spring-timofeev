package ru.dtimofeev.springapp.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookJpa должен")
@Import({BookJpa.class,GenreJpa.class})
class BookJpaTest {

    private static final long FIRST_BOOK_ID = 1L;
    private static final List<BookComment> FIRST_BOOK_COMMENTS_LIST = new ArrayList<>(Arrays.asList(new BookComment(1,"Супер, круто!"),
            new BookComment(2,"Потрясно!")
            ));
    private static final List<Author> FIRST_BOOK_AUTHORS_LIST = new ArrayList<>(Arrays.asList(new Author(1,"Михаил Булгаков")));
    private static final Genre FIRST_BOOK_GENRE = new Genre(1,"Классика");
    private static final Book FIRST_BOOK = new Book(1,"Мастер и Маргарита",FIRST_BOOK_GENRE,FIRST_BOOK_AUTHORS_LIST,FIRST_BOOK_COMMENTS_LIST);
    private static final Book BOOK_FOR_SAVE = new Book(0,"TestBook",FIRST_BOOK_GENRE, Collections.emptyList(),Collections.emptyList());
    private static final long BOOK_ID_FOR_DELETE = 14;

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private BookJpa bookJpa;
    @Autowired
    private GenreJpa genreJpa;

    @DisplayName(" возвращать корректно книгу по ID со всеми зависимыми сущностями")
    @Test
    void shouldCorrectReturnBookWithAllInfoById() {
        assertThat(bookJpa.findById(FIRST_BOOK_ID).get())
                .usingRecursiveComparison()
                .isEqualTo(FIRST_BOOK);
    }

    @DisplayName(" возвращать корректно все книги со всеми зависимыми сущностями")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        List<Book> listOfBooksWithAllInfo = bookJpa.findAll();
        assertThat(listOfBooksWithAllInfo)
                .allMatch(book -> !book.getName().equals(""))
                .allMatch(book -> book.getId() > 0)
                .allMatch(book -> book.getGenre()!=null)
                .allMatch(book -> !book.getAuthors().isEmpty() && book.getAuthors().size()>0)
                .allMatch(book -> !book.getBookComments().isEmpty() && book.getBookComments().size()>0);
    }

    @DisplayName(" сохранять книгу")
    @Test
    void shouldCorrectSaveBook() {
        Book book = bookJpa.save(BOOK_FOR_SAVE);
        assertThat(book)
                .usingRecursiveComparison()
                .isEqualTo(testEntityManager.find(Book.class,book.getId()));
    }

    @DisplayName(" апдейтить книгу по ID")
    @Test
    void shouldCorrectUpdateBookById() {
        val bookNew = bookJpa.save(BOOK_FOR_SAVE);
        val bookForUpdate = new Book(bookNew.getId(), "UpdatedBook",genreJpa.findById(3).get(),Collections.emptyList(),Collections.emptyList());
        bookJpa.updateById(bookForUpdate);
        testEntityManager.refresh(bookNew);
        assertThat(bookForUpdate)
                .usingRecursiveComparison()
                .isEqualTo(bookJpa.findById(bookNew.getId()).get());
    }

    @DisplayName(" удалять книгу по ID")
    @Test
    void shouldDeleteBookById() {
        val book = bookJpa.findById(BOOK_ID_FOR_DELETE).get();
        testEntityManager.detach(book);

        bookJpa.deleteById(BOOK_ID_FOR_DELETE);

        assertThat(bookJpa.findById(BOOK_ID_FOR_DELETE)).isEmpty();
    }
}