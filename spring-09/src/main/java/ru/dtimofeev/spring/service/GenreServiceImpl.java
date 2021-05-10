package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final IOService ioService;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao, IOService ioService) {
        this.genreDao = genreDao;
        this.ioService = ioService;
    }

    @Override
    public void printAll(){
        for (Genre g : genreDao.getAll()){
            ioService.out("ID: " + g.getId() + ", " + g.getName());
        }
    }



}
