package ru.dtimofeev.spring.service.existing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.domain.Author;

import java.util.List;


@Service
public class AuthorExistingService implements ObjectExistingService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorExistingService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void ifAbsentThenAddNew(String author){
        try {
            authorDao.getByFullName(author);
        }
        catch (EmptyResultDataAccessException e){
            authorDao.insert(new Author(authorDao.getNextSequenceVal(),author));
        }
    }

    @Override
    public void ifAbsentThenAddNew(List<String> authorList) {
        for (String author : authorList){
            try {
                authorDao.getByFullName(author);
            }
            catch (EmptyResultDataAccessException e){
                authorDao.insert(new Author(authorDao.getNextSequenceVal(),author));
            }
        }
    }
}
