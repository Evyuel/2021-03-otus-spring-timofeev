package ru.dtimofeev.springapp.anothermicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.dtimofeev.springapp.rest.dto.GenreDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс GenreRestServiceImpl должен")
@ExtendWith(MockitoExtension.class)
class GenreRestServiceImplTest {


    private static final String TOKEN = "THIS_IS_TOKEN";
    private static final String host = "http://localhost:8080/api";
    private final static GenreDto[] ARRAY_OF_GENRES = new GenreDto[]{new GenreDto(1, "Классика"),
            new GenreDto(2, "Детектив"),
            new GenreDto(3, "Фантастика")};
    private final static List<GenreDto> LIST_OF_GENRES = List.of(ARRAY_OF_GENRES);
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private JWTService jwtService;
    @InjectMocks
    private GenreRestServiceImpl genreRestService;

    @Test
    @DisplayName("корректно получать все жанры из другого микросервиса")
    void shouldCorrectAllGenres() {
        Mockito.when(jwtService.getActualToken()).thenReturn(TOKEN);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtService.getActualToken());
        HttpEntity<GenreDto[]> entity = new HttpEntity<>(null, headers);
        Mockito.when(restTemplate.exchange(host + "/genre/", HttpMethod.GET, entity, GenreDto[].class)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(ARRAY_OF_GENRES));

        assertThat(genreRestService.getAllGenres())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(LIST_OF_GENRES);
    }

    @Test
    @DisplayName("корректно отправлять жанр в другой микросервис")
    void shouldCorrectSendGenre() throws JsonProcessingException {
        GenreDto genreForSend = new GenreDto(4, "New Genre");

        Mockito.when(jwtService.getActualToken()).thenReturn(TOKEN);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtService.getActualToken());
        HttpEntity<GenreDto> entity = new HttpEntity<>(genreForSend, headers);
        Mockito.when(restTemplate.postForEntity(host + "/genre", entity, GenreDto.class))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(genreForSend));

        GenreDto sentGenre = genreRestService.sendGenre(genreForSend);

        assertThat(genreForSend).isEqualTo(sentGenre);
    }
}