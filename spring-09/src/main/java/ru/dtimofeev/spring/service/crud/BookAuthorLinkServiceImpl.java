package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;
@Service
public class BookAuthorLinkServiceImpl implements BookAuthorLinkService {

    private final BookAuthorLinkDao bookAuthorLinkDao;

    @Autowired
    public BookAuthorLinkServiceImpl(BookAuthorLinkDao bookAuthorLinkDao, AuthorDao authorDao) {
        this.bookAuthorLinkDao = bookAuthorLinkDao;
    }

    @Override
    public List<BookAuthorLink> getBookAuthorLinksByBookID(long bookId){
        return bookAuthorLinkDao.getBookAuthorLinksByBookID(bookId);
    }
}
