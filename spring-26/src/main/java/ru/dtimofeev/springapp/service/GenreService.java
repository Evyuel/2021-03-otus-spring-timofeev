package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.rest.dto.GenreDto;

import java.util.List;

public interface GenreService {
    GenreDto getById(long id);

    List<GenreDto> getAll();

    GenreDto getByName(String name);

    GenreDto update(long id, GenreDto genredto);

    GenreDto save(GenreDto genredto);

    void deleteById(long id);
}
