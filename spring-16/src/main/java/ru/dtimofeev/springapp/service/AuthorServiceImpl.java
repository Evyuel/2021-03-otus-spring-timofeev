package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.repositories.AuthorRepository;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;
import ru.dtimofeev.springapp.rest.dto.mapping.AuthorMapping;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapping authorMapping;
    private final String objectName = "Author";

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapping authorMapping) {
        this.authorRepository = authorRepository;
        this.authorMapping = authorMapping;
    }

    @Override
    public AuthorDto getById(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return authorMapping.toDto(author);
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(author -> authorMapping.toDto(author)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto save(AuthorDto authordto) {
        Author a = authorRepository.save(authorMapping.toEntity(authordto));
        return authorMapping.toDto(a);
    }
}
