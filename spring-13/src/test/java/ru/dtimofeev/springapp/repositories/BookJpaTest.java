//package ru.dtimofeev.springapp.repositories;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.annotation.Import;
//import ru.dtimofeev.springapp.models.Book;
//import ru.dtimofeev.springapp.models.Genre;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@DisplayName("Класс BookJpa должен")
//@Import({BookJpa.class, GenreJpa.class})
//class BookJpaTest {
//
//    private static final long FIRST_BOOK_ID = 1L;
//    private static final Genre FIRST_BOOK_GENRE = new Genre(1, "Классика");
//    private static final Book BOOK_FOR_SAVE = new Book(0, "TestBook", FIRST_BOOK_GENRE, Collections.emptyList(), Collections.emptyList());
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//    @Autowired
//    private BookJpa bookJpa;
//    @Autowired
//    private GenreJpa genreJpa;
//
//    @DisplayName(" возвращать корректно книгу по ID со всеми зависимыми сущностями")
//    @Test
//    void shouldCorrectReturnBookWithAllInfoById() {
//        assertThat(bookJpa.findById(FIRST_BOOK_ID).get())
//                .usingRecursiveComparison()
//                .isEqualTo(testEntityManager.find(Book.class, FIRST_BOOK_ID));
//    }
//
//    @DisplayName(" возвращать корректно все книги со всеми зависимыми сущностями")
//    @Test
//    void shouldReturnCorrectBookListWithAllInfo() {
//        List<Book> listOfBooksWithAllInfo = bookJpa.findAll();
//        assertThat(listOfBooksWithAllInfo)
//                .allMatch(book -> !book.getName().equals(""))
//                .allMatch(book -> book.getId() > 0)
//                .allMatch(book -> book.getGenre() != null)
//                .allMatch(book -> !book.getAuthors().isEmpty() && book.getAuthors().size() > 0)
//                .allMatch(book -> !book.getBookComments().isEmpty() && book.getBookComments().size() > 0);
//    }
//
//    @DisplayName(" сохранять книгу")
//    @Test
//    void shouldCorrectSaveBook() {
//        Book book = bookJpa.save(BOOK_FOR_SAVE);
//        assertThat(book)
//                .usingRecursiveComparison()
//                .isEqualTo(testEntityManager.find(Book.class, book.getId()));
//    }
//}