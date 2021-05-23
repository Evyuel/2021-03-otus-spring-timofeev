package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookCommentDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCommentProcessingServiceImpl implements BookCommentProcessingService {

    private final BookCommentDao bookCommentDao;

    @Autowired
    public BookCommentProcessingServiceImpl(BookCommentDao bookCommentDao) {
        this.bookCommentDao = bookCommentDao;
    }

    @Override
    public List<BookComment> saveBookCommentList(String bookComments, Book book) {
        List<BookComment> listOfBookComments = new ArrayList<>();
        for (String s : bookComments.split(",")) {
            listOfBookComments.add(bookCommentDao.save(new BookComment(0, s, book)));
        }
        return listOfBookComments;
    }

    @Override
    public void deleteCommentsByStringList(List<BookComment> listOfBookComments){
        List<Long> ids = new ArrayList<>();
        for (BookComment b : listOfBookComments){
            ids.add(b.getId());
        }
        bookCommentDao.deleteByIds(ids);
    }
}
