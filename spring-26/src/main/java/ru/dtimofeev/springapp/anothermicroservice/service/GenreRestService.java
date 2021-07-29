package ru.dtimofeev.springapp.anothermicroservice.service;

import ru.dtimofeev.springapp.rest.dto.GenreDto;

import java.util.List;

public interface GenreRestService {
    List<GenreDto> getAllGenres();

    GenreDto sendGenre(GenreDto genreDto);
}
