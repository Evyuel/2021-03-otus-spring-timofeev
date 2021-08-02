package ru.dtimofeev.springapp.service;

import io.micrometer.core.instrument.MockClock;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.rest.dto.mapping.AuthorMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.BookCommentMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.BookMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.GenreMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookServiceImplTest должен")
@Import({BookServiceImpl.class, BookMapping.class, AuthorMapping.class, GenreMapping.class, BookCommentMapping.class, SimpleMeterRegistry.class, MockClock.class})
class BookServiceImplTest {


    private static final Book BOOK_FOR_SAVE = new Book(0,
            "New Book",
            new Genre(2, "Детектив"),
            List.of(new Author(9, "Илья Ильф"), new Author(10, "Евгений Петров")),
            List.of(new BookComment(0, "TopComment1"), new BookComment(0, "TopComment2")));

    private static final Book BOOK_FOR_UPDATE = new Book(1,
            "Мастер и Маргарита_UPDATED",
            new Genre(2, "Детектив"),
            List.of(new Author(9, "Илья Ильф"), new Author(10, "Евгений Петров")),
            List.of(new BookComment(0, "TopComment1_UPD"), new BookComment(0, "TopComment1_UPD")));

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookServiceImpl bookProcessingService;

    @Autowired
    private BookMapping bookMapping;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SimpleMeterRegistry meterRegistry;

    @Test
    @DisplayName("корректно сохранять книгу")
    void shouldCorrectSaveBook() {
        Book savedBook = bookMapping.toEntity(bookProcessingService.save(bookMapping.toDto(BOOK_FOR_SAVE)));
        entityManager.flush();
        entityManager.clear();

        Book bookFromRepo = bookRepository.findById(savedBook.getId()).get();
        assertThat(bookFromRepo.getId() > 0);
        assertThat(bookFromRepo.getGenre().getName()).isEqualTo(BOOK_FOR_SAVE.getGenre().getName());
        assertThat(bookFromRepo.getName()).isEqualTo(BOOK_FOR_SAVE.getName());
        assertThat(bookFromRepo.getAuthors().size()).isEqualTo(2);
        assertThat(bookFromRepo.getBookComments().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("корректно апдейтить книгу")
    void shouldCorrectUpdateBook() {

        Book updatedBook = bookMapping.toEntity(bookProcessingService.update(BOOK_FOR_UPDATE.getId(), bookMapping.toDto(BOOK_FOR_UPDATE)));
        entityManager.flush();
        entityManager.clear();

        Book bookFromRepo = bookRepository.findById(updatedBook.getId()).get();
        assertThat(bookFromRepo.getId() > 0);
        assertThat(bookFromRepo.getGenre().getName()).isEqualTo(BOOK_FOR_UPDATE.getGenre().getName());
        assertThat(bookFromRepo.getName()).isEqualTo(BOOK_FOR_UPDATE.getName());
        assertThat(bookFromRepo.getAuthors().size()).isEqualTo(2);
        assertThat(bookFromRepo.getBookComments().size()).isEqualTo(2);
    }

}