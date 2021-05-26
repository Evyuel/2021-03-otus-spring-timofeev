package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenreID(long genreId) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "join fetch b.genre " +
                "where b.genre.id = :genreId", Book.class);
        query.setParameter("genreId", genreId);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findByName(String bookName) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "join fetch b.genre " +
                "where b.name = :bookName", Book.class);
        query.setParameter("bookName", bookName);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
        }
        return Optional.empty();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            try {
                entityManager.persist(book);
                return book;
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return entityManager.merge(book);
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

}
