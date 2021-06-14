package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookProcessingServiceImpl implements BookProcessingService {

    private final BookRepository bookRepository;

    @Autowired
    public BookProcessingServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Book saveBookWithAllInfo(Book book) {
        Book bookForSave = bookRepository.save(
                new Book(
                        0,
                        book.getName(),
                        book.getGenre(),
                        book.getAuthors(),
                        Collections.emptyList()));

        List<BookComment> bcList = book.getBookComments().stream().map(bookComment -> new BookComment(bookComment.getId(), bookComment.getCommentText(), bookForSave)).collect(Collectors.toList());
        bookForSave.setBookComments(bcList);
        return bookForSave;
    }

    @Transactional
    @Override
    public Book updateBookWithAllInfoByName(long id, Book book) {
        List<BookComment> bcList = book.getBookComments().stream().map(bookComment -> new BookComment(bookComment.getId(), bookComment.getCommentText(), book)).collect(Collectors.toList());
        Book bookForUpd = bookRepository.save(
                new Book(
                        id,
                        book.getName(),
                        book.getGenre(),
                        book.getAuthors(),
                        bcList));
        return bookForUpd;
    }
}


