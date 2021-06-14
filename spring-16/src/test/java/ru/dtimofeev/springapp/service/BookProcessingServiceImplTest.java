package ru.dtimofeev.springapp.service;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookProcessingServiceImpl должен")
@Import({BookProcessingServiceImpl.class})
class BookProcessingServiceImplTest {


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
    private BookProcessingServiceImpl bookProcessingService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("корректно сохранять книгу")
    void shouldCorrectSaveBook() {
        Book savedBook = bookProcessingService.saveBookWithAllInfo(BOOK_FOR_SAVE);
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

        Book updatedBook = bookProcessingService.updateBookWithAllInfoByName(BOOK_FOR_UPDATE.getId(), BOOK_FOR_UPDATE);
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