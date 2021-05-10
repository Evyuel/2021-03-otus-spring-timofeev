package ru.dtimofeev.spring.service;

public interface BookService {
    void printAll();

    void printBooksOfParticularGenre(long id);

    void addNewBookWithDependentEntities(String genreName, String authorsName, String bookName);

    void deleteBookByID(long id);
}
