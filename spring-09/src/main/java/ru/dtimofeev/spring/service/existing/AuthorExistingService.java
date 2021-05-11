package ru.dtimofeev.spring.service.existing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.service.crud.AuthorService;

import java.util.List;


@Service
public class AuthorExistingService implements ObjectExistingService {

    private final AuthorService authorService;

    @Autowired
    public AuthorExistingService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void ifAbsentThenAddNew(String authorFullName) {
        try {
            authorService.getByFullName(authorFullName);
        } catch (EmptyResultDataAccessException e) {
            authorService.insert(authorFullName);
        }
    }

    @Override
    public void ifAbsentThenAddNew(List<String> authorNameList) {
        for (String authorFullName : authorNameList) {
            try {
                authorService.getByFullName(authorFullName);
            } catch (EmptyResultDataAccessException e) {
                authorService.insert(authorFullName);
            }
        }
    }
}
