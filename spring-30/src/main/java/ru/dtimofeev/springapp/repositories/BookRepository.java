package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.dtimofeev.springapp.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    @EntityGraph(value = "book.genre")
    List<Book> findAll();

    @EntityGraph(value = "book.genre")
    Optional<Book> findById(@Param("id") Long id);

    @EntityGraph(value = "book.genre")
    List<Book> findByGenreId(long genreid);

    @EntityGraph(value = "book.genre")
    Optional<Book> findByName(String bookName);


}
