package ru.dtimofeev.springapp.rest.dto.mapping;

import org.springframework.stereotype.Component;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;

@Component
public class AuthorMapping {

    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }

    public Author toEntity(AuthorDto authordto) {
        return new Author(authordto.getId(), authordto.getFullName());
    }
}
