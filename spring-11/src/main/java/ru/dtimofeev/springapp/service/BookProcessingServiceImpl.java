package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookDao;
import ru.dtimofeev.springapp.repositories.GenreDao;

import java.util.List;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService io;
    private final BookDao bookDao;
    private final GenreProcessingService genreProcessingService;
    private final AuthorProcessingService authorProcessingService;
    private final BookCommentProcessingService bookCommentProcessingService;
    private final GenreDao genreDao;

    @Autowired
    public BookProcessingServiceImpl(IOService io,
                                     BookDao bookDao,
                                     GenreProcessingService genreProcessingService,
                                     AuthorProcessingService authorProcessingService,
                                     BookCommentProcessingService bookCommentProcessingService, GenreDao genreDao) {
        this.io = io;
        this.bookDao = bookDao;
        this.genreProcessingService = genreProcessingService;
        this.authorProcessingService = authorProcessingService;
        this.bookCommentProcessingService = bookCommentProcessingService;
        this.genreDao = genreDao;
    }

    @Transactional(readOnly = true)
    @Override
    public void printAll() {
        for (Book book : bookDao.findAll()) {
            io.out("ID: " + book.getId() + "; "
                    + book.getGenre().getName() + "; "
                    + getAuthorsFullNameInLine(book.getAuthors()) + "; "
                    + "\"" + book.getName() + "\"; "
                    + getBookCommentInLine(book.getBookComments())
            );
        }

    }

    @Transactional(readOnly = true)
    @Override
    public void printBooksOfParticularGenre(long id) {
        for (Book book : bookDao.findByGenre(genreDao.findById(id).get())) {
            io.out("ID: " + book.getId() + "; "
                    + book.getGenre().getName() + "; "
                    + getAuthorsFullNameInLine(book.getAuthors()) + "; "
                    + "\"" + book.getName() + "\"; "
                    + getBookCommentInLine(book.getBookComments())
            );
        }
    }

    @Transactional
    @Override
    public Book saveBookWithAllInfo(String genreName, String authorsName, String bookName, String comments) {
        if (bookDao.findByName(bookName).isEmpty()) {
            Book bookForSave = bookDao.save(
                    new Book(
                            0,
                            bookName,
                            genreProcessingService.saveGenre(genreName),
                            authorProcessingService.saveAuthorList(authorsName)));
            List<BookComment> bookCommentsList = bookCommentProcessingService.parseStringForListOfComments(comments, bookForSave);
            bookForSave.setBookComments(bookCommentsList);
            return bookForSave;
        } else {
            return bookDao.findByName(bookName).get();
        }
    }

    @Transactional
    @Override
    public void deleteBookWithAllInfoById(long id) {
        Book b = bookDao.findById(id).get();
        bookDao.delete(b);
    }

    @Transactional
    @Override
    public void updateBookWithAllInfoByName(String bookName, String genreName, String authorsName, String comments) {
        List<Author> listOfAuthors = authorProcessingService.saveAuthorList(authorsName);
        Book b = bookDao.findByName(bookName).get();
        Book updBook = new Book(b.getId(), b.getName(), genreDao.findByName(genreName).get(), b.getAuthors(), b.getBookComments());
        bookDao.save(updBook);
        updBook.getAuthors().clear();
        updBook.getAuthors().addAll(listOfAuthors);
        updBook.getBookComments().clear();
        List<BookComment> bl = bookCommentProcessingService.parseStringForListOfComments(comments, updBook);
        updBook.getBookComments().addAll(bl);

    }

    private String getAuthorsFullNameInLine(List<Author> listOfAuthors) {
        String s = "";
        int i = 0;
        for (Author a : listOfAuthors) {
            if (i == 0) {
                s = s.concat(a.getFullName());
            } else {
                s = s.concat(", " + a.getFullName());
            }
            i++;
        }
        return s;
    }

    private String getBookCommentInLine(List<BookComment> listOfBookComment) {
        String s = "";
        int i = 0;
        for (BookComment bc : listOfBookComment) {
            if (i == 0) {
                s = s.concat(bc.getCommentText());
            } else {
                s = s.concat(", " + bc.getCommentText());
            }
            i++;
        }
        return s;
    }
}


