package ru.dtimofeev.springapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;
    private String name;
    private GenreDto genre;
    private List<AuthorDto> authors;
    private List<BookCommentDto> bookComments = new ArrayList<>();


}
