package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Book;
import ru.dtimofeev.spring.domain.Genre;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final BookAuthorLinkService bookAuthorLinkService;

    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreDao genreDao, BookAuthorLinkService bookAuthorLinkService) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.bookAuthorLinkService = bookAuthorLinkService;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getByGenreID(long genreID) {
        return bookDao.getByGenreID(genreID);
    }

    @Override
    public Book getByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public void deleteBookByID(long id) {
        bookAuthorLinkService.deleteByBookID(id);
        bookDao.deleteById(id);
    }

    @Override
    public Map<Book, Genre> getAllWithGenre() {
        return bookDao.getAllWithGenre();
    }

    @Override
    public void insert(String bookName, String genreName, List<String> listOfAuthorsName) {
        int bookId = bookDao.getNextSequenceVal();
        bookDao.insert(new Book(bookId, bookName, genreDao.getByName(genreName).getId()));
        bookAuthorLinkService.insert(bookId, listOfAuthorsName);
    }

}
