package ru.dtimofeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCommentProcessingServiceImpl implements BookCommentProcessingService {

    @Override
    public List<BookComment> parseStringForListOfComments(String comments, Book b) {
        List<BookComment> listOfBookComments = new ArrayList<>();
        for (String s : comments.split(",")) {
            listOfBookComments.add(new BookComment(0, s, b));
        }
        return listOfBookComments;
    }

}
