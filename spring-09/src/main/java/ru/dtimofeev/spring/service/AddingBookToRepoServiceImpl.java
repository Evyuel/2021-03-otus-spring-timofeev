package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;

@Service
public class AddingBookToRepoServiceImpl implements AddingBookToRepoService {

    private final BookAuthorLinkDao bookAuthorLinkDao;
    private final BookDao bookDao;

    @Autowired
    public AddingBookToRepoServiceImpl(BookAuthorLinkDao bookAuthorLinkDao, BookDao bookDao) {
        this.bookAuthorLinkDao = bookAuthorLinkDao;
        this.bookDao = bookDao;
    }

    @Override
    public void addNewBookToRepo(String bookName, long genreID, List<Author> listOfAuthors){
        long bookID=bookDao.getNextSequenceVal();
        bookDao.insert(new Book(bookID,bookName,genreID));
        for (Author a : listOfAuthors){
            bookAuthorLinkDao.insert(new BookAuthorLink(bookAuthorLinkDao.getNextSequenceVal(),bookID,a.getId()));
        }

    }
}
