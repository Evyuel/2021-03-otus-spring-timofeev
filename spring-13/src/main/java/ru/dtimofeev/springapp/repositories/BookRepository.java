package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dtimofeev.springapp.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    @EntityGraph(value = "book.genre")
    List<Book> findAll();

    @EntityGraph(value = "book.genre")
    Optional<Book> findById(@Param("id") Long id);

    Book save(Book b);

    void delete(Book b);

    @EntityGraph(value = "book.genre")
    List<Book> findByGenreId( long genreid);

    //@Query("select b from Book b inner join fetch b.genre " +
    //        "where b.name=:name")
    Здесь првоерить без Query!
    Optional<Book> findByName(@Param("name") String bookName);


}
