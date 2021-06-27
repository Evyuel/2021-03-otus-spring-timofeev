package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.rest.dto.BookDto;
import ru.dtimofeev.springapp.rest.dto.mapping.BookMapping;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapping bookMapping;
    private final String objectName = "Book";

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapping bookMapping) {
        this.bookRepository = bookRepository;
        this.bookMapping = bookMapping;
    }

    @Override
    public BookDto getById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return bookMapping.toDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(book -> bookMapping.toDto(book)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto save(BookDto bookdto) {
        Book b = bookMapping.toEntity(bookdto);
        Book bookForSave = bookRepository.save(
                new Book(
                        b.getId(),
                        b.getName(),
                        b.getGenre(),
                        b.getAuthors(),
                        Collections.emptyList()));

        List<BookComment> bcList = b.getBookComments().stream().map(bookComment -> new BookComment(bookComment.getId(), bookComment.getCommentText(), bookForSave)).collect(Collectors.toList());
        bookForSave.setBookComments(bcList);
        return bookMapping.toDto(bookForSave);
    }

    @Transactional
    @Override
    public BookDto update(long id, BookDto bookdto) {
        Book b = bookMapping.toEntity(bookdto);
        List<BookComment> bcList = b.getBookComments().stream().map(bookComment -> new BookComment(bookComment.getId(), bookComment.getCommentText(), b)).collect(Collectors.toList());
        Book bookForUpd = bookRepository.save(
                new Book(
                        id,
                        b.getName(),
                        b.getGenre(),
                        b.getAuthors(),
                        bcList));
        return bookMapping.toDto(bookForUpd);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}


