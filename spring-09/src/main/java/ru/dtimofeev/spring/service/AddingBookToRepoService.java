package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Author;

import java.util.List;

public interface AddingBookToRepoService {
    void addNewBookToRepo(String bookName, long genreID, List<Author> listOfAuthors);
}
