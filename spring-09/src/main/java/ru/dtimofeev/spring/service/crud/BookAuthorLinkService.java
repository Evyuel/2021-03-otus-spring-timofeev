package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;

public interface BookAuthorLinkService {

    List<BookAuthorLink> getBookAuthorLinksByBookID(long bookId);

    void insert(long bookId, List<String> listOfAuthorsName);

    void deleteByBookID(long id);
}
