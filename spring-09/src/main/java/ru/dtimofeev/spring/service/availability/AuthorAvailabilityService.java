package ru.dtimofeev.spring.service.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.domain.Author;


@Service
public class AuthorAvailabilityService implements ObjectAvailabilityService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorAvailabilityService(AuthorDao authorDao) {
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
}
