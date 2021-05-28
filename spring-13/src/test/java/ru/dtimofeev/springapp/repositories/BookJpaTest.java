package ru.dtimofeev.springapp.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@DisplayName("Класс BookJpa должен")
class BookJpaTest {

    //private static final long FIRST_BOOK_ID = 1L;
    private static final Genre FIRST_BOOK_GENRE = new Genre(1, "Классика");
    private static final List<Author> LIST_OF_AUTHORS = List.of(new Author(3,"Агата Кристи"),new Author(1,"Михаил Булгаков"));
    private static final List<String> LIST_OF_AUTHORS_NAME = List.of("Агата Кристи","Михаил Булгаков");
    private static final List<String> LITS_OF_BOOK_COMMENTS_STRING = List.of("TopComment1","TopComment2");
    private static final Book BOOK_FOR_SAVE = new Book(0, "TestBook", FIRST_BOOK_GENRE, LIST_OF_AUTHORS);

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" сохранять книгу")
    @Test
    void shouldCorrectSaveBook() {
        Book savedBook = bookRepository.save(BOOK_FOR_SAVE);
        List<BookComment> LIST_OF_BOOK_COMMENTS = List.of(new BookComment(0,"TopComment1",savedBook),new BookComment(0,"TopComment2",savedBook));
        savedBook.setBookComments(LIST_OF_BOOK_COMMENTS);
        testEntityManager.flush();
        testEntityManager.clear();

        Book findedBook = testEntityManager.find(Book.class,savedBook.getId());
        List<String> commentsString = new ArrayList<>();
        for (BookComment bc : findedBook.getBookComments()){
            commentsString.add(bc.getCommentText());
        }
        List<String> authorsName = new ArrayList<>();
        for (Author a : findedBook.getAuthors()){
            authorsName.add(a.getFullName());
        }

        assertAll(() -> findedBook.getName().equals("TestBook"),
                () -> findedBook.getGenre().getName().equals("Классика"));
        assertThat(LIST_OF_AUTHORS_NAME).isEqualTo(authorsName);
        assertThat(LITS_OF_BOOK_COMMENTS_STRING).isEqualTo(commentsString);
    }
}