package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.models.Genre;

public interface GenreProcessingService {
    void printAll();

    Genre saveGenre(String genreName);
}
