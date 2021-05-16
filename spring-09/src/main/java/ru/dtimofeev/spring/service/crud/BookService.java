package ru.dtimofeev.spring.service.crud;

import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.Genre;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> getAll();

    List<Book> getByGenreID(long genreID);

    Book getByName(String name);

    void deleteBookByID(long id);

    Map<Book, Genre> getAllWithGenre();

    void insert(String bookName, String genreName, List<String> listOfAuthorsName);
}
