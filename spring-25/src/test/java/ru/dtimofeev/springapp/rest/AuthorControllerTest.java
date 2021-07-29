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
import ru.dtimofeev.springapp.repositories.AuthorRepository;
import ru.dtimofeev.springapp.rest.dto.AuthorDto;
import ru.dtimofeev.springapp.rest.dto.mapping.AuthorMapping;
import ru.dtimofeev.springapp.security.CustomUserDetailService;
import ru.dtimofeev.springapp.security.SecurityConfig;
import ru.dtimofeev.springapp.service.AuthorServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AuthorController.class, AuthorMapping.class, AuthorServiceImpl.class, SecurityConfig.class})
@DisplayName("Класс AuthorControllerTest должен ")
class AuthorControllerTest {
    private static final Author AUTHOR_WITH_ID_1 = new Author(1, "Михаил Булгаков");
    private static final Author AUTHOR_FOR_SAVE = new Author(1, "Михаил Булгаков_Updated");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AuthorMapping authorMapping;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно возвращать автора по ID")
    @Test
    void shouldCorrectReturnAuthorById() throws Exception {
        given(authorRepository.findById(AUTHOR_WITH_ID_1.getId())).willReturn(Optional.of(AUTHOR_WITH_ID_1));
        AuthorDto expectedAuthor = authorMapping.toDto(AUTHOR_WITH_ID_1);

        mvc.perform(get("/api/author/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedAuthor)));

    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно возвращать всех авторов")
    @Test
    void shouldCorrectReturnAllAuthors() throws Exception {
        List<Author> listOfAuthors = List.of(new Author(1, "First"), new Author(2, "Second"));
        given(authorRepository.findAll()).willReturn(listOfAuthors);

        List<AuthorDto> expectedAuthorList = listOfAuthors.stream().map(author -> authorMapping.toDto(author)).collect(Collectors.toList());

        mvc.perform(get("/api/author/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedAuthorList)));

    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("корректно сохранять автора")
    @Test
    void shouldCorrectSaveAuthor() throws Exception {

        given(authorRepository.save(AUTHOR_FOR_SAVE)).willReturn(new Author(5, AUTHOR_FOR_SAVE.getFullName()));
        AuthorDto expectedAuthor = authorMapping.toDto(AUTHOR_FOR_SAVE);

        mvc.perform(post("/api/author")
                .content(mapper.writeValueAsString(authorMapping.toDto(AUTHOR_FOR_SAVE)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value(AUTHOR_FOR_SAVE.getFullName()));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("запретить доступ не со своей ролью")
    @Test
    void shouldRedirect() throws Exception {
        mvc.perform(get("/api/author")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }


}