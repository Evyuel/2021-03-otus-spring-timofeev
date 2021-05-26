package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCommentProcessingServiceImpl implements BookCommentProcessingService {

    @Override
    public List<BookComment> parseStringForCommentList(String bookComments, Book book) {
        List<BookComment> listOfBookComments = new ArrayList<>();
        for (String s : bookComments.split(",")) {
            listOfBookComments.add(new BookComment(0, s, book));
        }
        return listOfBookComments;
    }
}
