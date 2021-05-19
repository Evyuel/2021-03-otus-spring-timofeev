package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.Genre;

import java.util.List;
import java.util.Map;

public interface BookDao {
    void insert(Book book);

    void updateById(Book book);

    void deleteById(long id);

    Book getById(long id);

    List<Book> getByGenreID(long id);

    Book getByName(String name);

    int getNextSequenceVal();

    List<Book> getAll();

    Map<Book, Genre> getAllWithGenre();
}
