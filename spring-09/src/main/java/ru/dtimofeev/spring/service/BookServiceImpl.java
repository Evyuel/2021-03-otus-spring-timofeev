package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookAuthorLinkDao;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.service.existing.ObjectExistingService;

import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final IOService ioService;
    private final AuthorDao authorDao;
    private final BookAuthorLinkDao bookAuthorLinkDao;
    private final BookAuthorLinkService bookAuthorLinkService;
    private final ObjectExistingService authorExistingService;
    private final ObjectExistingService genreExistingService;
    private final AddingBookToRepoService addingBookToRepoService;

    @Autowired
    public BookServiceImpl(BookDao bookDao,
                           AuthorDao authorDao,
                           GenreDao genreDao,
                           IOService ioService,
                           AuthorDao authorDao1, BookAuthorLinkDao bookAuthorLinkDao, BookAuthorLinkService bookAuthorLinkService,
                           @Qualifier("authorExistingService") ObjectExistingService authorExistingService,
                           @Qualifier("genreExistingService") ObjectExistingService genreExistingService,
                           AddingBookToRepoService addingBookToRepoService) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
        this.authorDao = authorDao1;
        this.bookAuthorLinkDao = bookAuthorLinkDao;
        this.bookAuthorLinkService = bookAuthorLinkService;
        this.authorExistingService = authorExistingService;
        this.genreExistingService = genreExistingService;
        this.addingBookToRepoService = addingBookToRepoService;
    }

    @Override
    public void printAll() {
        for (Book b : bookDao.getAll()) {
            ioService.out("ID: " + b.getId() + "; " + genreDao.getById(b.getGenreID()).getName() + "; " + bookAuthorLinkService.getAuthorsFullNameInLineByBookID(b.getId()) + "; \"" + b.getName() + "\"");
        }
    }

    @Override
    public void printBooksOfParticularGenre(long id) {
        for (Book b : bookDao.getByGenreID(id)) {
            ioService.out("ID: " + b.getId() + "; " + genreDao.getById(b.getGenreID()).getName() + "; " + bookAuthorLinkService.getAuthorsFullNameInLineByBookID(b.getId()) + "; \"" + b.getName() + "\"");
        }
    }

    @Override
    public void addNewBookWithDependentEntities(String genreName, String authorsName, String bookName) {
        List<Book> listOfBooks = bookDao.getByName(bookName);
        if (!listOfBooks.isEmpty()) {
            for (Book b : bookDao.getByName(bookName)) {
                if (b.getGenreID() != genreDao.getByName(genreName).getId() || !bookAuthorLinkDao.getAuthorsByBookID(b.getId()).equals(Arrays.asList(authorsName.split(",")))) {
                    addBookWithDependentEntities(genreName, authorsName, bookName);
                }
            }
        }
        else {
            addBookWithDependentEntities(genreName, authorsName, bookName);
        }
    }

    @Override
    public void deleteBookByID(long id) {
        bookDao.deleteById(id);
    }

    private void addBookWithDependentEntities(String genreName, String authorsName,String bookName){
        genreExistingService.ifAbsentThenAddNew(genreName);
        authorExistingService.ifAbsentThenAddNew(Arrays.asList(authorsName.split(",")));
        bookDao.insert(new Book(bookDao.getNextSequenceVal(),bookName,genreDao.getByName(genreName).getId()));
    }
}
