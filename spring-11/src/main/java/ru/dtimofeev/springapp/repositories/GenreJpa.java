package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Genre;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GenreJpa implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findByName(String genreName) {
        TypedQuery<Genre> query = entityManager.createQuery(" select g from Genre g " +
                "where g.name=:name", Genre.class);
        query.setParameter("name", genreName);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
        }
        return Optional.empty();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            entityManager.persist(genre);
            return genre;
        }
        return entityManager.merge(genre);
    }

    @Override
    public void updateById(Genre genre) {
        Query query = entityManager.createQuery("update Genre g " +
                "set g.name=:name " +
                "where g.id=:id");
        query.setParameter("id", genre.getId());
        query.setParameter("name", genre.getName());
        query.executeUpdate();
    }
}
