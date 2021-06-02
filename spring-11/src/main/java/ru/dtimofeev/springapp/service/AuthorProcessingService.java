package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.models.Author;

import java.util.List;

public interface AuthorProcessingService {
    List<Author> saveAuthorList(String authorsFullName);
}
