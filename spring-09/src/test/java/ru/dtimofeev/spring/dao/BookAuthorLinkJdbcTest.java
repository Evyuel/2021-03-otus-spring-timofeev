package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@DisplayName("Класс BookAuthorLinkJdbc должен ")
@Import({BookAuthorLinkJdbc.class,BookJdbc.class,AuthorJdbc.class})

class BookAuthorLinkJdbcTest {

    private static final int EXPECTED_SEQUENCE_INCREASE = 4;
    private static final int GENRE_ID_FOR_INSERT = 1;
    private static final String BOOK_NAME_FOR_INSERT = "BookWithID90";
    private static final int BOOK_ID_FOR_INSERT = 90;
    private static final int AUTHOR_ID_FOR_INSERT = 40;
    private static final String AUTHOR_NAME_FOR_INSERT = "AuthorTestName";
    private static final int LINK_ID_FOR_INSERT = 90;
    private static final int LINK_ID_FOR_GETTING = 1;
    private static final int BOOK_ID_FOR_GETTING = 1;
    private static final int AUTHOR_ID_FOR_GETTING = 1;
    private static final List<BookAuthorLink> BOOKAUTHORLINK_LIST_FOR_BOOK_ID8 = new ArrayList<>(Arrays.asList(
                                                                        new BookAuthorLink[]{new BookAuthorLink(9, 8,9),
                                                                                             new BookAuthorLink(10, 8,10)}
                                                                                                              )
                                                                                                );
    private static final int BOOK_ID8 = 8;

    @Autowired
    private BookAuthorLinkJdbc bookAuthorLinkJdbc;
    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private AuthorJdbc authorJdbc;
    @Autowired
    private JdbcTemplate jdbc;

    @DisplayName("корректно возвращать связь книги и автора по BookID")
    @Test
    void shouldCorrectReturnLinkByBookId() {
        assertThat(BOOKAUTHORLINK_LIST_FOR_BOOK_ID8)
                .usingFieldByFieldElementComparator()
                .isEqualTo(bookAuthorLinkJdbc.getBookAuthorLinksByBookID(BOOK_ID8));
    }

    @DisplayName("корректно возвращать связь книги и автора по LinkID")
    @Test
    void shouldCorrectReturnLinkByLinkId(){
        assertThat(new BookAuthorLink(LINK_ID_FOR_GETTING, BOOK_ID_FOR_GETTING, AUTHOR_ID_FOR_GETTING))
                .usingRecursiveComparison()
                .isEqualTo(bookAuthorLinkJdbc.getByLinkId(LINK_ID_FOR_GETTING));

    }

    @DisplayName("корректно переключать и возвращать значение счетчика")
    @Test
    void shouldReturnCorrectSequenceValue() {
        int seqCurVal = bookAuthorLinkJdbc.getNextSequenceVal();
        bookAuthorLinkJdbc.getNextSequenceVal();
        bookAuthorLinkJdbc.getNextSequenceVal();
        bookAuthorLinkJdbc.getNextSequenceVal();
        assertEquals(seqCurVal+EXPECTED_SEQUENCE_INCREASE,jdbc.queryForObject("select bookauthorlink_sq.nextval from dual",Integer.class));
    }

    @DisplayName("корректно добавлять связь автора и книги")
    @Test
    void shouldCorrectInsertLink() {
        bookJdbc.insert(new Book(BOOK_ID_FOR_INSERT, BOOK_NAME_FOR_INSERT, GENRE_ID_FOR_INSERT));
        authorJdbc.insert(new Author(AUTHOR_ID_FOR_INSERT, AUTHOR_NAME_FOR_INSERT));
        BookAuthorLink bookAuthorLink = new BookAuthorLink(LINK_ID_FOR_INSERT, BOOK_ID_FOR_INSERT, AUTHOR_ID_FOR_INSERT);
        bookAuthorLinkJdbc.insert(bookAuthorLink);
        assertThat(bookAuthorLink).usingRecursiveComparison().isEqualTo(bookAuthorLinkJdbc.getByLinkId(LINK_ID_FOR_INSERT));
    }
}