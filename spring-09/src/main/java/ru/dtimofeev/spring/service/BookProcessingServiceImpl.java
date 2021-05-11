package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.service.crud.AuthorService;
import ru.dtimofeev.spring.service.crud.BookAuthorLinkService;
import ru.dtimofeev.spring.service.crud.BookService;
import ru.dtimofeev.spring.service.crud.GenreService;
import ru.dtimofeev.spring.service.existing.ObjectExistingService;

import java.util.Arrays;
import java.util.List;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService ioService;
    private final AuthorProcessingServiceImpl authorProcessingService;
    private final ObjectExistingService authorExistingService;
    private final ObjectExistingService genreExistingService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;


    public BookProcessingServiceImpl(IOService ioService,
                                     AuthorProcessingServiceImpl authorProcessingService,
                                     @Qualifier("authorExistingService") ObjectExistingService authorExistingService,
                                     @Qualifier("genreExistingService") ObjectExistingService genreExistingService,
                                     AuthorService authorService,
                                     BookService bookService, GenreService genreService,
                                     BookAuthorLinkService bookAuthorLinkService) {
        this.ioService = ioService;
        this.authorProcessingService = authorProcessingService;
        this.authorExistingService = authorExistingService;
        this.genreExistingService = genreExistingService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @Override
    public void printAll() {
        for (Book b : bookService.getAll()) {
            ioService.out("ID: " + b.getId() + "; " + genreService.getById(b.getGenreID()).getName() + "; " + authorProcessingService.getAuthorsFullNameInLine(authorService.getAuthorListByBookID(b.getId())) + "; \"" + b.getName() + "\"");
        }
    }

    @Override
    public void printBooksOfParticularGenre(long id) {
        for (Book b : bookService.getByGenreID(id)) {
            ioService.out("ID: " + b.getId() + "; " + genreService.getById(b.getGenreID()).getName() + "; " + authorProcessingService.getAuthorsFullNameInLine(authorService.getAuthorListByBookID(b.getId())) + "; \"" + b.getName() + "\"");
        }
    }

    @Override
    public void addNewBookWithDependentEntities(String genreName, String authorsName, String bookName) {
        boolean isBookAlreadyExist = false;
        List<Book> listOfBooks = bookService.getByName(bookName);
        if (!listOfBooks.isEmpty()) {
            for (Book b : bookService.getByName(bookName)) {
                if (b.getGenreID() == genreService.getByName(genreName).getId() &&
                        authorService.getAuthorListByFullNameList(Arrays.asList(authorsName.split(","))).equals(authorService.getAuthorListByBookID(b.getId()))
                ) {
                    isBookAlreadyExist = true;
                }
            }
        }
        if (listOfBooks.isEmpty() || !isBookAlreadyExist)
            addBookWithDependentEntities(genreName, authorsName, bookName);
    }


    private void addBookWithDependentEntities(String genreName, String authorsName, String bookName) {
        genreExistingService.ifAbsentThenAddNew(genreName);
        authorExistingService.ifAbsentThenAddNew(Arrays.asList(authorsName.split(",")));
        bookService.insert(bookName,genreName);
    }
}
