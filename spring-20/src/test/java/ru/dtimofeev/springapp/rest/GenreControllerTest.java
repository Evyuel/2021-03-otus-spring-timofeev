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
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;
import ru.dtimofeev.springapp.rest.dto.GenreDto;
import ru.dtimofeev.springapp.rest.dto.mapping.GenreMapping;
import ru.dtimofeev.springapp.security.CustomUserDetailService;
import ru.dtimofeev.springapp.security.SecurityConfig;
import ru.dtimofeev.springapp.service.GenreServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({GenreController.class, GenreMapping.class, GenreServiceImpl.class, SecurityConfig.class})
@DisplayName("Класс GenreController должен ")
class GenreControllerTest {

    private static final Genre GENRE_WITH_ID_1 = new Genre(1, "Классика");
    private static final List<Genre> ALL_GENRES = List.of(new Genre(1, "Классика"), new Genre(2, "Детектив"), new Genre(3, "Фантастика"));
    private static final Genre GENRE_FOR_SAVE = new Genre(1, "Классика_Updated");
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GenreMapping genreMapping;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно возвращать жанр по ID")
    @Test
    void shouldCorrectReturnGenreById() throws Exception {
        given(genreRepository.findById(GENRE_WITH_ID_1.getId())).willReturn(Optional.of(GENRE_WITH_ID_1));
        GenreDto expectedGenre = genreMapping.toDto(GENRE_WITH_ID_1);

        mvc.perform(get("/api/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedGenre)));

    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно возвращать все жанры")
    @Test
    void shouldCorrectReturnAllGenres() throws Exception {

        given(genreRepository.findAll()).willReturn(ALL_GENRES);

        List<GenreDto> expectedGenreList = ALL_GENRES.stream().map(genre -> genreMapping.toDto(genre)).collect(Collectors.toList());

        mvc.perform(get("/api/genre/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedGenreList)));

    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно возвращать жанр по имени")
    @Test
    void shouldCorrectReturnGenreByParam() throws Exception {

        given(genreRepository.findByName("Классика")).willReturn(Optional.of(GENRE_WITH_ID_1));
        GenreDto expectedGenre = genreMapping.toDto(GENRE_WITH_ID_1);

        mvc.perform(get("/api/genre/search?name=Классика"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedGenre)));

    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно апдейтить жанр")
    @Test
    void shouldCorrectUpdateGenre() throws Exception {

        given(genreRepository.save(GENRE_FOR_SAVE)).willReturn(GENRE_FOR_SAVE);
        GenreDto expectedGenre = genreMapping.toDto(GENRE_FOR_SAVE);

        mvc.perform(put("/api/genre/{id}", GENRE_FOR_SAVE.getId())
                .content(mapper.writeValueAsString(GENRE_FOR_SAVE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedGenre)));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно сохранять жанр")
    @Test
    void shouldCorrectSaveGenre() throws Exception {

        given(genreRepository.save(GENRE_FOR_SAVE)).willReturn(new Genre(4, GENRE_FOR_SAVE.getName()));
        GenreDto expectedGenre = genreMapping.toDto(GENRE_FOR_SAVE);

        mvc.perform(post("/api/genre")
                .content(mapper.writeValueAsString(genreMapping.toDto(GENRE_FOR_SAVE)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(GENRE_FOR_SAVE.getName()));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("корректно удалять жанр")
    @Test
    void shouldCorrectDeleteGenre() throws Exception {

        mvc.perform(delete("/api/genre/{id}", 1))
                .andExpect(status().isAccepted());
    }

    @WithMockUser(
            username = "SomeUser",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("запретить доступ не со своей ролью")
    @Test
    void shouldRedirect() throws Exception {
        mvc.perform(get("/api/genre")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}