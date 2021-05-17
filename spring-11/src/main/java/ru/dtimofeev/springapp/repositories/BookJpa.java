package ru.dtimofeev.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.dtimofeev.springapp.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                "join fetch b.genre",Book.class);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId()==0){
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }

    @Override
    public void updateById(Book book) {
        Query query = entityManager.createQuery("update Book b " +
                "set b.name=:name, b.genre=:genre " +
                "where b.id=:id");
        query.setParameter("name",book.getName());
        query.setParameter("genre",book.getGenre());
        query.setParameter("id",book.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete Book b " +
                "where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
