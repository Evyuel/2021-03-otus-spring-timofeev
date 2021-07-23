package ru.dtimofeev.springapp.rest.dto.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.rest.dto.BookDto;

import java.util.stream.Collectors;

@Component
public class BookMapping {

    private final GenreMapping genreMapping;
    private final AuthorMapping authorMapping;
    private final BookCommentMapping bookCommentMapping;

    @Autowired
    public BookMapping(GenreMapping genreMapping, AuthorMapping authorMapping, BookCommentMapping bookCommentMapping) {
        this.genreMapping = genreMapping;
        this.authorMapping = authorMapping;
        this.bookCommentMapping = bookCommentMapping;
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                genreMapping.toDto(book.getGenre()),
                book.getAuthors().stream().map(author -> authorMapping.toDto(author)).collect(Collectors.toList()),
                book.getBookComments().stream().map(bookComment -> bookCommentMapping.toDto(bookComment)).collect(Collectors.toList()));
    }

    public Book toEntity(BookDto bookdto) {
        return new Book(bookdto.getId(),
                bookdto.getName(),
                genreMapping.toEntity(bookdto.getGenre()),
                bookdto.getAuthors().stream().map(author -> authorMapping.toEntity(author)).collect(Collectors.toList()),
                bookdto.getBookComments().stream().map(bookComment -> bookCommentMapping.toEntity(bookComment)).collect(Collectors.toList()));
    }
}
