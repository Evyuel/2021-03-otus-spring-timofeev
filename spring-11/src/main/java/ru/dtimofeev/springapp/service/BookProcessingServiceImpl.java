package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.repositories.AuthorDao;
import ru.dtimofeev.springapp.repositories.BookDao;

import javax.transaction.Transactional;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService io;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookProcessingServiceImpl(IOService io, BookDao bookDao, AuthorDao authorDao) {
        this.io = io;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Transactional
    @Override
    public void printAll(){
        for (Book book : bookDao.findAll()){
            io.out(book.getAuthors().toString());
        }

    }

    @Override
    @Transactional
    public void test(){
        for (Author author : authorDao.findAll()){
            io.out(author.getBook().getName());
        }

    }
}
