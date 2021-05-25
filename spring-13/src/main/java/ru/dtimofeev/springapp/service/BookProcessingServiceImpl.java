package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.BookCommentRepository;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.repositories.GenreRepository;

import java.util.List;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final IOService io;
    private final GenreProcessingService genreProcessingService;
    private final AuthorProcessingService authorProcessingService;
    private final BookCommentProcessingService bookCommentProcessingService;
    private final BookCommentRepository bookCommentRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookProcessingServiceImpl(IOService io,
                                     GenreProcessingService genreProcessingService,
                                     AuthorProcessingService authorProcessingService,
                                     BookCommentProcessingService bookCommentProcessingService,
                                     BookCommentRepository bookCommentRepository,
                                     GenreRepository genreRepository,
                                     BookRepository bookRepository) {
        this.io = io;
        this.bookRepository = bookRepository;
        this.genreProcessingService = genreProcessingService;
        this.authorProcessingService = authorProcessingService;
        this.bookCommentProcessingService = bookCommentProcessingService;
        this.bookCommentRepository = bookCommentRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public void printAll() {
        for (Book book : bookRepository.findAll()) {
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
    public void printBooksOfParticularGenre(long id) {
        for (Book book : bookRepository.findByGenreId(id)) {
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
        if (bookRepository.findByName(bookName).isEmpty()) {
            Genre genre = genreProcessingService.saveGenre(genreName);
            List<Author> listOfAuthors = authorProcessingService.saveAuthorList(authorsName);
            Book book = bookRepository.save(new Book(0, bookName, genre, listOfAuthors));
            bookCommentProcessingService.saveBookCommentList(comments, book);
            return book;
        } else {
            return bookRepository.findByName(bookName).get();
        }
    }

    @Transactional
    @Override
    public void deleteBookWithAllInfoById(long id) {
        Book b = bookRepository.findById(id).get();
        bookCommentRepository.deleteInBatch(b.getBookComments());
        bookRepository.deleteById(b.getId());
    }

    @Transactional
    @Override
    public void updateBookWithAllInfoByName(String bookName, String genreName, String authorsName, String comments) {
        List<Author> listOfAuthors = authorProcessingService.saveAuthorList(authorsName);
        Book b = bookRepository.findByName(bookName).get();
        Book updBook = new Book(b.getId(), b.getName(), genreRepository.findByName(genreName).get(), b.getAuthors(), b.getBookComments());
        bookRepository.save(updBook);
        b.getAuthors().clear();
        b.getAuthors().addAll(listOfAuthors);
        bookCommentRepository.deleteInBatch(b.getBookComments());
        bookCommentProcessingService.saveBookCommentList(comments, b);
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


