package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);

    void updateById(Author author);

    void deleteById(long id);

    Author getById(long id);

    List<Author> getByIds(List<Long> ids);

    Author getByFullName(String fullName);

    List<Author> getAll();

    int getNextSequenceVal();
}
