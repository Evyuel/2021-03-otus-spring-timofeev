package ru.dtimofeev.springapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.repositories.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@DisplayName("Класс BookProcessingServiceImpl должен")
@Import({BookProcessingServiceImpl.class,
        IOServiceImpl.class,
        GenreProcessingServiceImpl.class,
        AuthorProcessingServiceImpl.class,
        BookCommentProcessingServiceImpl.class})
class BookProcessingServiceImplTest {

    private final static String STRING_GENRE = "NewGenre";
    private final static String STRING_AUTHORS = "NewAuthor1,NewAuthor2";
    private final static String STRING_BOOK = "NewBook";
    private final static String STRING_BOOK_COMMENTS = "Wow1!,Wow2!";
    private final static String STRING_GENRE_FOR_UPDATE = "Классика";
    private final static String STRING_AUTHORS_FOR_UPDATE = "Михаил Булгаков";
    private final static String STRING_BOOK_COMMENTS_FOR_UPDATE = "New1,New2,New3,New4";
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookProcessingServiceImpl bookProcessingService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("корректно сохранять книгу с авторами,жанрами и комментариями, если отсутствуют в БД")
    void shouldCorrectAddBookWithAllInfo() {
        Book newBook = bookProcessingService.saveBookWithAllInfo(STRING_GENRE, STRING_AUTHORS, STRING_BOOK, STRING_BOOK_COMMENTS);
        entityManager.flush();
        entityManager.clear();

        Book bookFromRepo = bookRepository.findById(newBook.getId()).get();
        assertAll(() -> bookFromRepo.getName().equals(STRING_BOOK),
                () -> bookFromRepo.getGenre().getName().equals(STRING_GENRE)
        );
        assertThat(bookFromRepo.getAuthors().size()).isEqualTo(2);
        assertThat(bookFromRepo.getBookComments().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("корректно удалять книгу с комментариями и связями с автором")
    void shouldDeleteBookWithCommentAndAuthorLinks() {
        Book newBook = bookProcessingService.saveBookWithAllInfo(STRING_GENRE, STRING_AUTHORS, STRING_BOOK, STRING_BOOK_COMMENTS);
        entityManager.flush();
        entityManager.clear();

        bookProcessingService.deleteBookWithAllInfoById(newBook.getId());
        entityManager.flush();
        entityManager.clear();

        assertThat(bookRepository.findById(newBook.getId())).isEmpty();
    }

    @Test
    @DisplayName("корректно апдейтить книгу(жанр,комменты,авторов) по ее наименованию")
    void shouldUpdateBookWithAllInfoByName() {
        Book newBook = bookProcessingService.saveBookWithAllInfo(STRING_GENRE, STRING_AUTHORS, STRING_BOOK, STRING_BOOK_COMMENTS);
        entityManager.flush();
        entityManager.clear();

        bookProcessingService.updateBookWithAllInfoByName(STRING_BOOK, STRING_GENRE_FOR_UPDATE, STRING_AUTHORS_FOR_UPDATE, STRING_BOOK_COMMENTS_FOR_UPDATE);
        entityManager.flush();
        entityManager.clear();

        Book updBook = bookRepository.findById(newBook.getId()).get();

        assertAll(() -> updBook.getName().equals(STRING_BOOK),
                () -> updBook.getGenre().getName().equals(STRING_GENRE_FOR_UPDATE)
        );
        assertThat(updBook.getAuthors().size()).isEqualTo(1);
        assertThat(updBook.getBookComments().size()).isEqualTo(4);
    }

}