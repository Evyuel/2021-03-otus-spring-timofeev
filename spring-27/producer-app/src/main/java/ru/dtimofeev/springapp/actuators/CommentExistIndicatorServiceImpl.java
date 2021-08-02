package ru.dtimofeev.springapp.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.repositories.BookRepository;

@Service
public class CommentExistIndicatorServiceImpl implements CommentExistIndicatorService {

    private final BookRepository bookRepository;

    @Autowired
    public CommentExistIndicatorServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public boolean isThereBooksWithoutComments() {
        int count = 0;
        for (Book b : bookRepository.findAll()) {
            if (b.getBookComments().isEmpty()) {
                count++;
            }
        }
        return count > 0;
    }

}
