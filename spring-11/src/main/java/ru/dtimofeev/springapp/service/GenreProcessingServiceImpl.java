package ru.dtimofeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreDao;

@Service
public class GenreProcessingServiceImpl implements GenreProcessingService {

    private final GenreDao genreDao;
    private final IOService ioService;

    public GenreProcessingServiceImpl(GenreDao genreDao, IOService ioService) {
        this.genreDao = genreDao;
        this.ioService = ioService;
    }

    @Override
    public void printAll() {
        for (Genre g : genreDao.findAll()) {
            ioService.out("ID: " + g.getId() + ", " + g.getName());
        }
    }

    @Override
    public Genre saveGenre(String genreName) {
        if (genreDao.findByName(genreName).isEmpty()) {
            return genreDao.save(new Genre(0, genreName));
        } else {
            return genreDao.findByName(genreName).get();
        }
    }
}
