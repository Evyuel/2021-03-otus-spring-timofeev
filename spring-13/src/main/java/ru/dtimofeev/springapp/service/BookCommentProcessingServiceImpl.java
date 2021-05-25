package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookCommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCommentProcessingServiceImpl implements BookCommentProcessingService {

    private final BookCommentRepository bookCommentRepository;

    @Autowired
    public BookCommentProcessingServiceImpl(BookCommentRepository bookCommentRepository) {
        this.bookCommentRepository = bookCommentRepository;
    }

    @Override
    public List<BookComment> saveBookCommentList(String bookComments, Book book) {
        List<BookComment> listOfBookComments = new ArrayList<>();
        for (String s : bookComments.split(",")) {
            listOfBookComments.add(bookCommentRepository.save(new BookComment(0, s, book)));
        }
        return listOfBookComments;
    }
}
