package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;
import java.util.Map;

public interface BookAuthorLinkDao {
    void insert(BookAuthorLink bookAuthorLink);

    void deleteByBookId(long BookId);

    BookAuthorLink getByLinkId(long linkId);

    List<BookAuthorLink> getBookAuthorLinksByBookID(long bookID);

    Map<Long, List<Author>> getAuthorsOfAllBooks();

    List<Long> getAuthorIDListByBookID(long id);

    int getNextSequenceVal();
}
