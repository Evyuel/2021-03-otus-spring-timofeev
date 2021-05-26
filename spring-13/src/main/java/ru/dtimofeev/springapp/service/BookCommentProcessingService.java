package ru.dtimofeev.springapp.service;

import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;

import java.util.List;

public interface BookCommentProcessingService {
    List<BookComment> parseStringForCommentList(String bookComments, Book book);
}
