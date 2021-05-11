package ru.dtimofeev.spring.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.domain.Author;

import java.util.List;

@Service
public class AuthorProcessingServiceImpl implements AuthorProcessingService {

    @Override
    public String getAuthorsFullNameInLine(List<Author> listOfAuthors) {
        String s = "";
        int i = 0;
        for (Author a : listOfAuthors) {
            if (i == 0) {
                s = s.concat(a.getFullName());
            } else {
                s = s.concat(", " + a.getFullName());
            }
            i++;
        }
        return s;
    }
}
