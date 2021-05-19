package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;
import java.util.Map;

public interface BookAuthorLinkService {

    List<BookAuthorLink> getBookAuthorLinksByBookID(long bookId);

    void insert(long bookId, List<String> listOfAuthorsName);

    Map<Long, List<Author>> getAuthorsOfAllBooks();

    void deleteByBookID(long id);
}
