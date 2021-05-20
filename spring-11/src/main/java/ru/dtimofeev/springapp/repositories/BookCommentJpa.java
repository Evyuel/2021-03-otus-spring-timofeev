package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class BookCommentJpa implements BookCommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookComment save(BookComment bookComment) {
        if (bookComment.getId() == 0) {
            entityManager.persist(bookComment);
            return bookComment;
        }
        return entityManager.merge(bookComment);
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete BookComment b " +
                "where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
