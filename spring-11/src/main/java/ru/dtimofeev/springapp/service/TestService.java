package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.repositories.BookDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestService {

    private final BookDao bookDao;

    @Autowired
    public TestService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void doSmthng() {
        List<Author> l = bookDao.findById(1).get().getAuthors();
        System.out.println(l.size());
        for (Author a : l) {
            System.out.println("ID: " + a.getId() + "fullname: " + a.getFullName());
        }

        List<BookComment> bk = bookDao.findById(1).get().getBookComments();
        System.out.println(bk.size());
        for (BookComment b : bk) {
            System.out.println(b.getCommentText());
        }
    }
}
