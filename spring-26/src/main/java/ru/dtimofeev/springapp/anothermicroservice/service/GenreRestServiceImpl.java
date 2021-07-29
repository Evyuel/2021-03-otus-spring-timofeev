package ru.dtimofeev.springapp.anothermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dtimofeev.springapp.rest.dto.GenreDto;

import java.util.List;

@Service
public class GenreRestServiceImpl implements GenreRestService {

    private final String host = "http://localhost:8080/api";
    private final JWTService jwtService;
    private final RestTemplate restTemplate;

    @Autowired
    public GenreRestServiceImpl(JWTService jwtService, RestTemplate restTemplate, RestTemplate restTemplate1) {
        this.jwtService = jwtService;
        this.restTemplate = restTemplate1;
    }

    @Override
    public List<GenreDto> getAllGenres() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtService.getActualToken());
        HttpEntity<GenreDto[]> entity = new HttpEntity<>(null, headers);
        ResponseEntity<GenreDto[]> responseEntity = restTemplate.exchange(host + "/genre/", HttpMethod.GET, entity, GenreDto[].class);
        return List.of(responseEntity.getBody());
    }

    @Override
    public GenreDto sendGenre(GenreDto genreDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtService.getActualToken());
        HttpEntity<GenreDto> entity = new HttpEntity<>(genreDto, headers);
        ResponseEntity<GenreDto> responseEntity = restTemplate.postForEntity(host + "/genre", entity, GenreDto.class);
        return responseEntity.getBody();
    }
}
