package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookJpa implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void updateById(Book book) {
    }

    @Override
    public void deleteById(long id) {
    }

}
