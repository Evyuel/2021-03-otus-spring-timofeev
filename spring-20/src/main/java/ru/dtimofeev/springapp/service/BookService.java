package ru.dtimofeev.springapp.service;


import ru.dtimofeev.springapp.rest.dto.BookDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService {

    BookDto getById(long id);

    List<BookDto> getAll();

    BookDto save(BookDto bookdto);

    @Transactional
    BookDto update(long id, BookDto bookdto);

    void deleteById(long id);
}
