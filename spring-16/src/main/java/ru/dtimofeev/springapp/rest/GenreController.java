package ru.dtimofeev.springapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;
import ru.dtimofeev.springapp.rest.dto.GenreDto;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;
    private final String objectName = "Genre";

    @GetMapping(value = "/api/genre/{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable("id") long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return new ResponseEntity<GenreDto>(GenreDto.toDto(genre), HttpStatus.OK);
    }

    @GetMapping(value = "/api/genre/")
    public List<GenreDto> getAll() {
        return genreRepository.findAll().stream().map(genre -> GenreDto.toDto(genre)).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/genre/search")
    public GenreDto getByName(@RequestParam("name") String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException(objectName, "name", name));
        return GenreDto.toDto(genre);
    }

    @PutMapping(value = "/api/genre/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable("id") long id, @RequestBody Genre genre) {
        return new ResponseEntity<GenreDto>(GenreDto.toDto(genreRepository.save(genre)), HttpStatus.OK);
    }

    @PostMapping(value = "/api/genre")
    public ResponseEntity<GenreDto> save(@RequestBody Genre genre) {
        return new ResponseEntity<GenreDto>(GenreDto.toDto(genreRepository.save(new Genre(0, genre.getName()))), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/genre/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        genreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
