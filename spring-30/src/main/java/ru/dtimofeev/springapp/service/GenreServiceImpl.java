package ru.dtimofeev.springapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;
import ru.dtimofeev.springapp.rest.dto.GenreDto;
import ru.dtimofeev.springapp.rest.dto.mapping.GenreMapping;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapping genreMapping;
    private final String objectName = "Genre";

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapping genreMapping) {
        this.genreRepository = genreRepository;
        this.genreMapping = genreMapping;
    }

    @HystrixCommand(commandKey="genreKey", fallbackMethod="getSingleGenre")
    @Override
    public GenreDto getById(long id) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return genreMapping.toDto(genre);
    }

    @HystrixCommand(commandKey="genreKey", fallbackMethod="getListOfGenres")
    @Override
    public List<GenreDto> getAll() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return genreRepository.findAll().stream().map(genre -> genreMapping.toDto(genre)).collect(Collectors.toList());
    }

    @HystrixCommand(commandKey="genreKey", fallbackMethod="getSingleGenre")
    @Override
    public GenreDto getByName(String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException(objectName, "name", name));
        return genreMapping.toDto(genre);
    }

    @Override
    public GenreDto update(long id, GenreDto genredto) {
        Genre g = genreRepository.save(genreMapping.toEntity(genredto));
        return genreMapping.toDto(g);
    }

    @Override
    public GenreDto save(GenreDto genredto) {
        Genre g = genreRepository.save(genreMapping.toEntity(genredto));
        return genreMapping.toDto(g);
    }

    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    public GenreDto getSingleGenre(long id){
        return new GenreDto(0,"This_default_Genre");
    }

    public List<GenreDto> getListOfGenres(){
        return List.of(new GenreDto(0,"This_Genre_1"),new GenreDto(0,"This_Genre_2"));
    }

}
