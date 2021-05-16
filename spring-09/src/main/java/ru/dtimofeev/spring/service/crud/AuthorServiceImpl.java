package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final BookAuthorLinkDao bookAuthorLinkDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, BookAuthorLinkDao bookAuthorLinkDao) {
        this.authorDao = authorDao;
        this.bookAuthorLinkDao = bookAuthorLinkDao;
    }

    @Override
    public List<Author> getAuthorListByBookID(long id) {
        return authorDao.getByIds(bookAuthorLinkDao.getAuthorIDListByBookID(id));
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public void insert(String authorFullName) {
        authorDao.insert(new Author(authorDao.getNextSequenceVal(), authorFullName));
    }

    @Override
    public Author getByFullName(String authorFullName) {
        return authorDao.getByFullName(authorFullName);
    }
}
