package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.List;
import java.util.Map;

@Service
public class BookAuthorLinkServiceImpl implements BookAuthorLinkService {

    private final BookAuthorLinkDao bookAuthorLinkDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookAuthorLinkServiceImpl(BookAuthorLinkDao bookAuthorLinkDao, AuthorDao authorDao) {
        this.bookAuthorLinkDao = bookAuthorLinkDao;
        this.authorDao = authorDao;
    }

    @Override
    public List<BookAuthorLink> getBookAuthorLinksByBookID(long bookId) {
        return bookAuthorLinkDao.getBookAuthorLinksByBookID(bookId);
    }

    @Override
    public void insert(long bookId, List<String> listOfAuthorsName) {
        for (String authorName : listOfAuthorsName) {
            bookAuthorLinkDao.insert(new BookAuthorLink(bookAuthorLinkDao.getNextSequenceVal(), bookId, authorDao.getByFullName(authorName).getId()));
        }
    }

    @Override
    public Map<Long, List<Author>> getAuthorsOfAllBooks() {
        return bookAuthorLinkDao.getAuthorsOfAllBooks();
    }

    @Override
    public void deleteByBookID(long bookId) {
        bookAuthorLinkDao.deleteByBookId(bookId);
    }

}
