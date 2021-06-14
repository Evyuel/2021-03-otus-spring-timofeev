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

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                GenreDto.toDto(book.getGenre()),
                book.getAuthors().stream().map(author -> AuthorDto.toDto(author)).collect(Collectors.toList()),
                book.getBookComments().stream().map(bookComment -> BookCommentDto.toDto(bookComment)).collect(Collectors.toList()));
    }
}
