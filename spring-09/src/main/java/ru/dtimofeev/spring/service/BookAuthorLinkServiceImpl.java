package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;

import java.util.ArrayList;
import java.util.List;

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
    public String getAuthorsFullNameInLineByBookID(long id){
        String s = "";
        int i = 0;
        for (BookAuthorLink b : bookAuthorLinkDao.getAuthorsByBookID(id)){
            if (i==0) {
            s = s.concat(authorDao.getById(b.getAuthorID()).getFullName());}
            else {s = s.concat(", " + authorDao.getById(b.getAuthorID()).getFullName());}
            i++;
        }
        return s;
    }

    @Override
    public List<Author> getAuthorListByBookID(long id){
        List<Author> l = new ArrayList<>();
        for (BookAuthorLink bal : bookAuthorLinkDao.getAuthorsByBookID(id)){
            l.add(authorDao.getById(bal.getAuthorID()));
        }
        return l;
    }


}
