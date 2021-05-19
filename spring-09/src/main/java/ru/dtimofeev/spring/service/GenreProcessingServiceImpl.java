package ru.dtimofeev.spring.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Genre;

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
        for (Genre g : genreDao.getAll()) {
            ioService.out("ID: " + g.getId() + ", " + g.getName());
        }
    }
}
