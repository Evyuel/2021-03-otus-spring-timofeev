package ru.dtimofeev.spring.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.dao.GenreDao;
import ru.dtimofeev.spring.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    @Override
    public List<Book> getAll(){
        return bookDao.getAll();
    }

    @Override
    public List<Book> getByGenreID(long genreID){
        return bookDao.getByGenreID(genreID);
    }

    @Override
    public List<Book> getByName(String name){
        return bookDao.getByName(name);
    }
    @Override
    public void deleteBookByID(long id) {
        bookDao.deleteById(id);
    }
    @Override
    public void insert(String bookName, String genreName){
        bookDao.insert(new Book(bookDao.getNextSequenceVal(),bookName,genreDao.getByName(genreName).getId()));
    }

}
