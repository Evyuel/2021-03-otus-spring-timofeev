package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            entityManager.persist(author);
            return author;
        }
        return entityManager.merge(author);
    }

    @Override
    public void updateById(Author author) {
        Query query = entityManager.createQuery("update Author a " +
                "set a.fullName=:fullName " +
                "where a.id=:id");
        query.setParameter("id", author.getId());
        query.setParameter("fullName", author.getFullName());
        query.executeUpdate();
    }

}
