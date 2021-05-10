package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Author;

import java.util.List;

public interface BookAuthorLinkService {
    String getAuthorsFullNameInLineByBookID(long id);

    List<Author> getAuthorListByBookID(long id);
}
