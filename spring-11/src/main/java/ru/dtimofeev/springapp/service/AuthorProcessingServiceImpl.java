package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.repositories.AuthorDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorProcessingServiceImpl implements AuthorProcessingService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorProcessingServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> saveAuthorList(String authorsFullName) {
        List<Author> listOfAuthors = new ArrayList<>();
        for (String s : authorsFullName.split(",")) {
            if (authorDao.findByName(authorsFullName).isEmpty()) {
                listOfAuthors.add(authorDao.save(new Author(0, s)));
            } else {
                listOfAuthors.add(authorDao.findByName(authorsFullName).get());
            }
        }
        return listOfAuthors;
    }

}
