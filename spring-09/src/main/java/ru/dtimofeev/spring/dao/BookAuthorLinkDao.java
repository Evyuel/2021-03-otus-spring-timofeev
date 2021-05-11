package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;

public interface BookAuthorLinkDao {
    void insert(BookAuthorLink bookAuthorLink);

    void deleteByBookId(long BookId);

    BookAuthorLink getByLinkId(long linkId);

    List<BookAuthorLink> getBookAuthorLinksByBookID(long bookID);

    int getNextSequenceVal();
}
