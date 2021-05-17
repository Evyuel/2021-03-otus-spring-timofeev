package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book save(Book book);

    void updateById(Book book);

    void deleteById(long id);
}
