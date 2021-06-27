package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.springapp.models.Author;

@Data
@AllArgsConstructor
public class AuthorDto {

    private long id;
    private String fullName;

}