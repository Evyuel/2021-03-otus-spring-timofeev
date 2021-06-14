package ru.dtimofeev.springapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.rest.dto.BookDto;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;
import ru.dtimofeev.springapp.service.BookProcessingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    private final BookProcessingService bookProcessingService;
    private final String objectName = "Book";

    @GetMapping(value = "/api/book/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return new ResponseEntity<BookDto>(BookDto.toDto(book), HttpStatus.OK);
    }

    @GetMapping(value = "/api/book/")
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(book -> BookDto.toDto(book)).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/book")
    public ResponseEntity<BookDto> save(@RequestBody Book book) {
        BookDto bookDto = BookDto.toDto(bookProcessingService.saveBookWithAllInfo(book));
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/book/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") long id, @RequestBody Book book) {
        BookDto bookDto = BookDto.toDto(bookProcessingService.updateBookWithAllInfoByName(id, book));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/book/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
