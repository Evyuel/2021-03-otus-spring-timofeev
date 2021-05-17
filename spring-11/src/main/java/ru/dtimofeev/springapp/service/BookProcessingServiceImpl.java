package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.repositories.BookDao;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService io;
    private final BookDao bookDao;

    @Autowired
    public BookProcessingServiceImpl(IOService io, BookDao bookDao) {
        this.io = io;
        this.bookDao = bookDao;
    }

    @Override
    public void printAll(){
        io.out(bookDao.findAll().toString());
    }
}
