package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dtimofeev.springapp.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b inner join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b inner join fetch b.genre " +
            "where b.id=:id")
    Optional<Book> findById(@Param("id") Long id);

    Book save(Book b);

    void delete(Book b);

    @Query(value = "select b from Book b where b.genre.id=:genreid")
    List<Book> findByGenreId(@Param(value = "genreid") long genreid);

    @Query("select b from Book b inner join fetch b.genre " +
            "where b.name=:name")
    Optional<Book> findByName(@Param("name") String bookName);


}
