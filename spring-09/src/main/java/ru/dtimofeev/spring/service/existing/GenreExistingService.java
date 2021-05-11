package ru.dtimofeev.spring.service.existing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.service.crud.GenreService;

import java.util.List;

@Service
public class GenreExistingService implements ObjectExistingService {

    private final GenreService genreService;

    @Autowired
    public GenreExistingService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public void ifAbsentThenAddNew(String genreName) {
        try {
            genreService.getByName(genreName);
        } catch (EmptyResultDataAccessException e) {
            genreService.insert(genreName);
        }
    }

    @Override
    public void ifAbsentThenAddNew(List<String> genreNameList) {
        for (String genreName : genreNameList) {
            try {
                genreService.getByName(genreName);
            } catch (EmptyResultDataAccessException e) {
                genreService.insert(genreName);
            }
        }

    }
}
