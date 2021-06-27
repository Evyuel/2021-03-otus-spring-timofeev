package ru.dtimofeev.springapp.rest.dto.mapping;

import org.springframework.stereotype.Component;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.rest.dto.GenreDto;

@Component
public class GenreMapping {

    public GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
    public Genre toEntity(GenreDto genreDto){
        return new Genre(genreDto.getId(),genreDto.getName());
    }
}
