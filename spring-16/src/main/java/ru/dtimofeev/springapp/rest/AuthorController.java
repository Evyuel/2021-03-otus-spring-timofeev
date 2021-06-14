package ru.dtimofeev.springapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.repositories.AuthorRepository;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final String objectName = "Author";

    @GetMapping(value = "/api/author/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return new ResponseEntity<AuthorDto>(AuthorDto.toDto(author), HttpStatus.OK);
    }

    @GetMapping(value = "/api/author/")
    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream().map(author -> AuthorDto.toDto(author)).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/author")
    public ResponseEntity<AuthorDto> save(@RequestBody Author author) {
        return new ResponseEntity<AuthorDto>(AuthorDto.toDto(authorRepository.save(new Author(0, author.getFullName()))), HttpStatus.CREATED);
    }
}
