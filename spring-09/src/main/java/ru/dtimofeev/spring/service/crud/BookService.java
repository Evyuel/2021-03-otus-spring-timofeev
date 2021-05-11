package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getByGenreID(long genreID);

    List<Book> getByName(String name);

    void deleteBookByID(long id);

    void insert(String bookName, String genreName);
}
