package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;

public interface BookAuthorLinkDao {
    void insert(BookAuthorLink bookAuthorLink);

    List<BookAuthorLink> getAuthorsByBookID(long bookID);

    int getNextSequenceVal();
}
