package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.rest.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getById(long id);

    List<AuthorDto> getAll();

    AuthorDto save(AuthorDto authordto);
}
