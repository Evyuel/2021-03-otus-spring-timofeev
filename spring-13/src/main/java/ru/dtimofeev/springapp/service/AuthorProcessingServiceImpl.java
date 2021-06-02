package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorProcessingServiceImpl implements AuthorProcessingService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorProcessingServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> saveAuthorList(String authorsFullName) {
        List<Author> listOfAuthors = new ArrayList<>();
        for (String s : authorsFullName.split(",")) {
            if (authorRepository.findByFullName(authorsFullName).isEmpty()) {
                listOfAuthors.add(authorRepository.save(new Author(0, s)));
            } else {
                listOfAuthors.add(authorRepository.findByFullName(authorsFullName).get());
            }
        }
        return listOfAuthors;
    }

}
