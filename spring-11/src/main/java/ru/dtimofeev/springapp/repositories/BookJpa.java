package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.Genre;

import javax.persistence.*;
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
        EntityGraph<?> eg = entityManager.getEntityGraph("book.genre");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b ", Book.class);
        query.setHint("javax.persistence.fetchgraph",eg);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        EntityGraph<?> eg = entityManager.getEntityGraph("book.genre");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "where b.genre = :genre", Book.class);
        query.setHint("javax.persistence.fetchgraph",eg);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findByName(String bookName) {
        EntityGraph<?> eg = entityManager.getEntityGraph("book.genre");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "where b.name = :bookName", Book.class);
        query.setHint("javax.persistence.fetchgraph",eg);
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
