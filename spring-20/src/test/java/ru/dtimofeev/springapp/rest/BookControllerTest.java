package ru.dtimofeev.springapp.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.dtimofeev.springapp.models.Author;
import ru.dtimofeev.springapp.models.Book;
import ru.dtimofeev.springapp.models.BookComment;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.BookRepository;
import ru.dtimofeev.springapp.rest.dto.BookDto;
import ru.dtimofeev.springapp.rest.dto.mapping.AuthorMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.BookCommentMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.BookMapping;
import ru.dtimofeev.springapp.rest.dto.mapping.GenreMapping;
import ru.dtimofeev.springapp.security.CustomUserDetailService;
import ru.dtimofeev.springapp.security.SecurityConfig;
import ru.dtimofeev.springapp.service.BookServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookController.class,
        BookMapping.class, AuthorMapping.class, GenreMapping.class, BookCommentMapping.class,
        BookServiceImpl.class, SecurityConfig.class})
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

    @Autowired
    private BookMapping bookMapping;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookServiceImpl bookServiceImpl;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно возвращать книгу по ID")
    @Test
    void shouldCorrectReturnBookById() throws Exception {

        given(bookServiceImpl.getById(BOOK_WITH_ID_1.getId())).willReturn(bookMapping.toDto(BOOK_WITH_ID_1));
        BookDto expectedBook = bookMapping.toDto(BOOK_WITH_ID_1);

        mvc.perform(get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBook)));

    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно возвращать все книги")
    @Test
    void shouldCorrectReturnAllBooks() throws Exception {
        List<BookDto> expectedBookList = LIST_OF_BOOKS.stream().map(book -> bookMapping.toDto(book)).collect(Collectors.toList());
        given(bookServiceImpl.getAll()).willReturn(expectedBookList);

        mvc.perform(get("/api/book/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBookList)));

    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно сохранять книгу")
    @Test
    void shouldCorrectSaveBook() throws Exception {

        given(bookServiceImpl.save(bookMapping.toDto(BOOK_WITH_ID_1))).willReturn(bookMapping.toDto(NEW_BOOK));
        BookDto expectedBook = bookMapping.toDto(NEW_BOOK);

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

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно апдейтить книгу")
    @Test
    void shouldCorrectUpdateBook() throws Exception {

        given(bookServiceImpl.update(BOOK_WITH_ID_1.getId(), bookMapping.toDto(BOOK_WITH_ID_1))).willReturn(bookMapping.toDto(BOOK_WITH_ID_1));

        BookDto expectedBook = bookMapping.toDto(BOOK_WITH_ID_1);

        mvc.perform(put("/api/book/{id}", BOOK_WITH_ID_1.getId())
                .content(mapper.writeValueAsString(BOOK_WITH_ID_1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBook)));
    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно удалять книгу")
    @Test
    void shouldCorrectDeleteGenre() throws Exception {

        mvc.perform(delete("/api/book/{id}", 1))
                .andExpect(status().isAccepted());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("запретить доступ не со своей ролью")
    @Test
    void shouldRedirect() throws Exception {
        mvc.perform(get("/api/book")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}