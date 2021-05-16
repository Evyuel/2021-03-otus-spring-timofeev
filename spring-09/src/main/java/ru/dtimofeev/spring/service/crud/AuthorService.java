package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthorListByBookID(long id);

    Author getById(long id);

    void insert(String authorFullName);

    Author getByFullName(String authorFullName);
}
