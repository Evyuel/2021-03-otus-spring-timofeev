package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.BookAuthorLink;


import java.util.ArrayList;
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
    public List<Author> getAuthorListByFullNameList(List<String> fullNameList){
        List<Author> listOfAuthor = new ArrayList<>();
        for (String s : fullNameList){
            listOfAuthor.add(authorDao.getByFullName(s));
        }
        return listOfAuthor;
    }

    @Override
    public List<Author> getAuthorListByBookID(long id){
        List<Author> authorList = new ArrayList<>();
        for (BookAuthorLink bal : bookAuthorLinkDao.getBookAuthorLinksByBookID(id)){
            authorList.add(authorDao.getById(bal.getAuthorID()));
        }
        return authorList;
    }

    @Override
    public List<Author> getAuthorListByBookAuthorLinkList(List<BookAuthorLink> list){
        final ArrayList<Author> authorList = new ArrayList<>();
        for(BookAuthorLink bal : list){
            authorList.add(authorDao.getById(bal.getAuthorID()));
        }
        return authorList;
    }

    @Override
    public Author getById(long id){
        return authorDao.getById(id);
    }

    @Override
    public void insert(String authorFullName){
        authorDao.insert(new Author(authorDao.getNextSequenceVal(),authorFullName));
    }
    @Override
    public Author getByFullName(String authorFullName){
        return authorDao.getByFullName(authorFullName);
    }
}
