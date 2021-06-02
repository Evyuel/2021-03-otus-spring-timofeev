package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByGenre(Genre genre);

    Optional<Book> findByName(String bookName);

    Book save(Book book);

    void delete(Book book);
}
