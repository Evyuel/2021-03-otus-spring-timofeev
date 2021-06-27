package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dtimofeev.springapp.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;
    private String name;
    private GenreDto genre;
    private List<AuthorDto> authors;
    private List<BookCommentDto> bookComments = new ArrayList<>();


}
