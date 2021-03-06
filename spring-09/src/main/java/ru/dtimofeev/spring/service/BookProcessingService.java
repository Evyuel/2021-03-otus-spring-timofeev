package ru.dtimofeev.spring.service;

public interface BookProcessingService {
    void printAll();

    void printBooksOfParticularGenre(long id);

    void checkAndAddNewBook(String genreName, String authorsName, String bookName);
}
