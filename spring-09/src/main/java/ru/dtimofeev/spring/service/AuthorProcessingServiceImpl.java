package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.service.crud.AuthorService;

import java.util.List;
@Service
public class AuthorProcessingServiceImpl implements AuthorProcessingService {

    private final AuthorService authorService;

    public AuthorProcessingServiceImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public String getAuthorsFullNameInLine(List<Author> listOfAuthors){
        String s = "";
        int i = 0;
        for (Author a : listOfAuthors){
            if (i==0) {
                s = s.concat(authorService.getById(a.getId()).getFullName());}
            else {s = s.concat(", " + authorService.getById(a.getId()).getFullName());}
            i++;
        }
        return s;
    }
}
