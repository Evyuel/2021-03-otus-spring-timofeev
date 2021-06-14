package ru.dtimofeev.springapp.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.rest.dto.BookDto;
import ru.dtimofeev.springapp.service.BookProcessingServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@DisplayName("Класс BookController должен ")
class BookControllerTest {

    private static final Book BOOK_WITH_ID_1 = new Book(1,
            "Мастер и Маргарита",
            new Genre(1, "Классика"),
            List.of(new Author(1, "Михаил Булгаков")),
            List.of(new BookComment(1, "Супер, круто!"), new BookComment(2, "Потрясно!"))
    );
    private static final Book BOOK_WITH_ID_2 = new Book(2,
            "Мёртвые души",
            new Genre(1, "Классика"),
            List.of(new Author(2, "Николай Гоголь")),
            List.of(new BookComment(30, "СуперКомментДля2книги!"), new BookComment(5, "ПростоКомментДля2книги!"))
    );
    private static final List<Book> LIST_OF_BOOKS = List.of(BOOK_WITH_ID_1, BOOK_WITH_ID_2);
    private static final Book NEW_BOOK = new Book(99,
            "Мастер и Маргарита",
            new Genre(1, "Классика"),
            List.of(new Author(1, "Михаил Булгаков")),
            List.of(new BookComment(1, "Супер, круто!"), new BookComment(2, "Потрясно!"))
    );

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookProcessingServiceImpl bookProcessingService;

    @DisplayName("корректно возвращать книгу по ID")
    @Test
    void shouldCorrectReturnBookById() throws Exception {

        given(bookRepository.findById(BOOK_WITH_ID_1.getId())).willReturn(Optional.of(BOOK_WITH_ID_1));
        BookDto expectedBook = BookDto.toDto(BOOK_WITH_ID_1);

        mvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBook)));

    }

    @DisplayName("корректно возвращать все книги")
    @Test
    void shouldCorrectReturnAllBooks() throws Exception {
        given(bookRepository.findAll()).willReturn(LIST_OF_BOOKS);

        List<BookDto> expectedBookList = LIST_OF_BOOKS.stream().map(book -> BookDto.toDto(book)).collect(Collectors.toList());

        mvc.perform(get("/api/book/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBookList)));

    }

    @DisplayName("корректно сохранять книгу")
    @Test
    void shouldCorrectSaveBook() throws Exception {

        given(bookProcessingService.saveBookWithAllInfo(BOOK_WITH_ID_1)).willReturn(NEW_BOOK);
        BookDto expectedBook = BookDto.toDto(NEW_BOOK);

        mvc.perform(post("/api/book")
                .content(mapper.writeValueAsString(BOOK_WITH_ID_1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(BOOK_WITH_ID_1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre.id").value(BOOK_WITH_ID_1.getGenre().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre.name").value(BOOK_WITH_ID_1.getGenre().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authors.length()").value(BOOK_WITH_ID_1.getAuthors().size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookComments.length()").value(BOOK_WITH_ID_1.getBookComments().size()));
    }

    @DisplayName("корректно апдейтить книгу")
    @Test
    void shouldCorrectUpdateBook() throws Exception {

        given(bookProcessingService.updateBookWithAllInfoByName(BOOK_WITH_ID_1.getId(), BOOK_WITH_ID_1)).willReturn(BOOK_WITH_ID_1);

        BookDto expectedBook = BookDto.toDto(BOOK_WITH_ID_1);

        mvc.perform(put("/api/book/{id}", BOOK_WITH_ID_1.getId())
                .content(mapper.writeValueAsString(BOOK_WITH_ID_1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBook)));
    }

    @DisplayName("корректно удалять книгу")
    @Test
    void shouldCorrectDeleteGenre() throws Exception {

        mvc.perform(delete("/api/book/{id}", 1))
                .andExpect(status().isAccepted());
    }

}