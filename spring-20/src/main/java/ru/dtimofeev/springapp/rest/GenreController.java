package ru.dtimofeev.springapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.rest.dto.GenreDto;
import ru.dtimofeev.springapp.service.GenreService;

import java.util.List;


@RestController
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/api/genre/{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable("id") long id) {
        return new ResponseEntity<GenreDto>(genreService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/api/genre/")
    public List<GenreDto> getAll() {
        return genreService.getAll();
    }

    @GetMapping(value = "/api/genre/search")
    public GenreDto getByName(@RequestParam("name") String name) {
        return genreService.getByName(name);
    }

    @PutMapping(value = "/api/genre/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable("id") long id, @RequestBody GenreDto genredto) {
        return new ResponseEntity<GenreDto>(genreService.update(id, genredto), HttpStatus.OK);
    }

    @PostMapping(value = "/api/genre")
    public ResponseEntity<GenreDto> save(@RequestBody GenreDto genredto) {
        return new ResponseEntity<GenreDto>(genreService.save(genredto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/genre/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        genreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
