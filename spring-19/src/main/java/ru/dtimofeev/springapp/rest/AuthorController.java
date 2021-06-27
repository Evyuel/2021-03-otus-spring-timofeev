package ru.dtimofeev.springapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;
import ru.dtimofeev.springapp.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/api/author/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") long id) {
        return new ResponseEntity<AuthorDto>(authorService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/api/author/")
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @PostMapping(value = "/api/author")
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authordto) {
        return new ResponseEntity<AuthorDto>(authorService.save(authordto), HttpStatus.CREATED);
    }
}
