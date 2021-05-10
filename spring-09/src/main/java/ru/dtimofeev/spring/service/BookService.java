package ru.dtimofeev.spring.service;

public interface BookService {
    void printAll();

    void printBooksOfParticularGenre(long id);

    void addNewBook(String genreName, String authorName, String bookName);

    void deleteBookByID(long id);
}
