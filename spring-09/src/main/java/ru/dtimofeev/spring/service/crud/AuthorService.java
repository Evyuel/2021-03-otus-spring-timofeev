package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthorListByFullNameList(List<String> fullNameList);

    List<Author> getAuthorListByBookID(long id);

    List<Author> getAuthorListByBookAuthorLinkList(List<BookAuthorLink> list);

    Author getById(long id);

    void insert(String authorFullName);

    Author getByFullName(String authorFullName);
}
