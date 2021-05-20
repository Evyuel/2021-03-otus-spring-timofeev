package ru.dtimofeev.springapp.service;


import ru.dtimofeev.springapp.models.Book;

import javax.transaction.Transactional;

public interface BookProcessingService {

    void printAll();

    @Transactional
    void printBooksOfParticularGenre(long id);

    Book saveBookWithAllInfo(String genreName, String authorsName, String bookName, String comments);

    @Transactional
    void deleteBookWithAllInfoById(long id);
}
