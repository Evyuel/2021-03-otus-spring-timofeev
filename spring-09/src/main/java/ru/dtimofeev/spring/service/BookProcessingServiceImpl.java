package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.domain.Author;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.Genre;
import ru.dtimofeev.spring.service.crud.AuthorService;
import ru.dtimofeev.spring.service.crud.BookAuthorLinkService;
import ru.dtimofeev.spring.service.crud.BookService;
import ru.dtimofeev.spring.service.crud.GenreService;
import ru.dtimofeev.spring.service.existing.ObjectExistingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService ioService;
    private final AuthorProcessingServiceImpl authorProcessingService;
    private final BookAuthorLinkService bookAuthorLinkService;
    private final ObjectExistingService authorExistingService;
    private final ObjectExistingService genreExistingService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;


    public BookProcessingServiceImpl(IOService ioService,
                                     AuthorProcessingServiceImpl authorProcessingService,
                                     BookAuthorLinkService bookAuthorLinkService, @Qualifier("authorExistingService") ObjectExistingService authorExistingService,
                                     @Qualifier("genreExistingService") ObjectExistingService genreExistingService,
                                     AuthorService authorService,
                                     BookService bookService,
                                     GenreService genreService) {
        this.ioService = ioService;
        this.authorProcessingService = authorProcessingService;
        this.bookAuthorLinkService = bookAuthorLinkService;
        this.authorExistingService = authorExistingService;
        this.genreExistingService = genreExistingService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @Override
    public void printAll() {
        final Map<Long, List<Author>> mapWithAuthors = bookAuthorLinkService.getAuthorsOfAllBooks();
        for (Map.Entry<Book, Genre> ent : bookService.getAllWithGenre().entrySet()) {
            ioService.out("ID: " + ent.getKey().getId() + "; " + ent.getValue().getName() + "; " + authorProcessingService.getAuthorsFullNameInLine(mapWithAuthors.get(ent.getKey().getId())) + "; \"" + ent.getKey().getName() + "\"");
        }
    }

    @Override
    public void printBooksOfParticularGenre(long id) {
        for (Book b : bookService.getByGenreID(id)) {
            ioService.out("ID: " + b.getId() + "; " + genreService.getById(b.getGenreID()).getName() + "; " + authorProcessingService.getAuthorsFullNameInLine(authorService.getAuthorListByBookID(b.getId())) + "; \"" + b.getName() + "\"");
        }
    }

    @Override
    public void checkAndAddNewBook(String genreName, String authorsName, String bookName) {
        try {
            bookService.getByName(bookName);
            ioService.out("Данная книга уже присутствует в библиотеке");
        } catch (EmptyResultDataAccessException e) {
            addBookWithDependentEntities(genreName, authorsName, bookName);
        }
    }


    private void addBookWithDependentEntities(String genreName, String authorsName, String bookName) {
        List<String> authorNameList = new ArrayList<>();
        for (String s : authorsName.split(",")) {
            authorNameList.add(s);
        }
        genreExistingService.ifAbsentThenAddNew(genreName);
        authorExistingService.ifAbsentThenAddNew(authorNameList);
        bookService.insert(bookName, genreName, authorNameList);
    }
}
