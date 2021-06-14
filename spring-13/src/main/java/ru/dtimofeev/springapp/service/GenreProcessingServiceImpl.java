package ru.dtimofeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;

@Service
public class GenreProcessingServiceImpl implements GenreProcessingService {

    private final IOService ioService;
    private final GenreRepository genreRepository;

    public GenreProcessingServiceImpl(IOService ioService, GenreRepository genreRepository) {
        this.ioService = ioService;
        this.genreRepository = genreRepository;
    }

    @Override
    public void printAll() {
        for (Genre g : genreRepository.findAll()) {
            ioService.out("ID: " + g.getId() + ", " + g.getName());
        }
    }

    @Override
    public Genre saveGenre(String genreName) {
        if (genreRepository.findByName(genreName).isEmpty()) {
            return genreRepository.save(new Genre(0, genreName));
        } else {
            return genreRepository.findByName(genreName).get();
        }
    }
}
