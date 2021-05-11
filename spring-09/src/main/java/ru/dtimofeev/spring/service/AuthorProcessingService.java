package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Author;

import java.util.List;

public interface AuthorProcessingService {
    String getAuthorsFullNameInLine(List<Author> listOfAuthors);
}
