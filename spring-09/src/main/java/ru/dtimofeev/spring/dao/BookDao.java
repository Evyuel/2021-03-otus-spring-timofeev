package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book book);

    void updateById(Book book);

    void deleteById(long id);

    Book getById(long id);

    List<Book> getAll();
}
