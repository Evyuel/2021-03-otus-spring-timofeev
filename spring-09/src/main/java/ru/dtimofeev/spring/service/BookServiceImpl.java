package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.AuthorDao;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.service.availability.ObjectAvailabilityService;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IOService ioService;
    private final ObjectAvailabilityService authorAvailabilityService;
    private final ObjectAvailabilityService genreAvailabilityService;

    @Autowired
    public BookServiceImpl(BookDao bookDao,
                           AuthorDao authorDao,
                           GenreDao genreDao,
                           IOService ioService,
                           @Qualifier("authorAvailabilityService")ObjectAvailabilityService authorAvailabilityService,
                           @Qualifier("genreAvailabilityService")ObjectAvailabilityService genreAvailabilityService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
        this.authorAvailabilityService=authorAvailabilityService;
        this.genreAvailabilityService=genreAvailabilityService;

    }

    @Override
    public void printAll() {
        for (Book b : bookDao.getAll()) {
            ioService.out("ID: " + b.getId() + ", " + genreDao.getById(b.getGenreID()).getName() + ", " + authorDao.getById(b.getAuthorID()).getFullName() + ", \"" + b.getName() + "\"");
        }
    }

    @Override
    public void printBooksOfParticularGenre(long id) {
        for (Book b : bookDao.getByGenreID(id)) {
            ioService.out("ID: " + b.getId() + ", " + genreDao.getById(b.getGenreID()).getName() + ", " + authorDao.getById(b.getAuthorID()).getFullName() + ", \"" + b.getName() + "\"");
        }
    }

    @Override
    public void addNewBook(String genreName, String authorName, String bookName){
        authorAvailabilityService.ifAbsentThenAddNew(authorName);
        genreAvailabilityService.ifAbsentThenAddNew(genreName);
        long authorID = authorDao.getByFullName(authorName).getId();
        long genreID = genreDao.getByName(genreName).getId();
        try {
            Book bookInRep = bookDao.getByName(bookName);
             if (bookInRep.getAuthorID() == authorID && bookInRep.getGenreID() == genreID){
                 ioService.out("Такая книга уже присутствует в библиотеке");
             }
             else {
                 bookDao.insert(new Book(bookDao.getNextSequenceVal(),bookName,authorID,genreID));
             }
        }
        catch (EmptyResultDataAccessException e){
            bookDao.insert(new Book(bookDao.getNextSequenceVal(),bookName,authorID,genreID));
        }

    }

    @Override
    public void deleteBookByID(long id){
        bookDao.deleteById(id);
    }
}
