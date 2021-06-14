package ru.dtimofeev.springapp.service;


import ru.dtimofeev.springapp.models.Book;

import javax.transaction.Transactional;

public interface BookProcessingService {

    Book saveBookWithAllInfo(Book book);

    @Transactional
    Book updateBookWithAllInfoByName(long id, Book book);
}
