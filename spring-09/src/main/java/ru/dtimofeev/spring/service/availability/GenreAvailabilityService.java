package ru.dtimofeev.spring.service.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.Genre;

@Service
public class GenreAvailabilityService implements ObjectAvailabilityService {

    private final GenreDao genreDao;

    @Autowired
    public GenreAvailabilityService(GenreDao genreDao) {
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
}
