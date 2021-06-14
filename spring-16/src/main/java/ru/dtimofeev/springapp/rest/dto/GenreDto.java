package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.springapp.models.Genre;

@Data
@AllArgsConstructor
public class GenreDto {

    private long id;
    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
