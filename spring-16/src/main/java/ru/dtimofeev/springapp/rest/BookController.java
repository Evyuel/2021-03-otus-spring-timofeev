package ru.dtimofeev.springapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.rest.dto.BookDto;
import ru.dtimofeev.springapp.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(value = "/api/book/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") long id) {
        return new ResponseEntity<BookDto>(bookService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/api/book/")
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping(value = "/api/book")
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookdto) {
        return new ResponseEntity<>(bookService.save(bookdto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/book/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") long id, @RequestBody BookDto bookdto) {
        return new ResponseEntity<>(bookService.update(id, bookdto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/book/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
