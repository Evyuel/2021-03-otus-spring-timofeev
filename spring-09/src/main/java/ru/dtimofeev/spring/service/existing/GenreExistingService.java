package ru.dtimofeev.spring.service.existing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Genre;

import java.util.List;

@Service
public class GenreExistingService implements ObjectExistingService {

    private final GenreDao genreDao;

    @Autowired
    public GenreExistingService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void ifAbsentThenAddNew(String genre){
        try {
            genreDao.getByName(genre);
        }
        catch (EmptyResultDataAccessException e){
            genreDao.insert(new Genre(genreDao.getNextSequenceVal(),genre));
        }
    }

    @Override
    public void ifAbsentThenAddNew(List<String> genreList) {
        for (String genre : genreList){
            try {
                genreDao.getByName(genre);
            }
            catch (EmptyResultDataAccessException e){
                genreDao.insert(new Genre(genreDao.getNextSequenceVal(),genre));
            }
        }

    }
}
