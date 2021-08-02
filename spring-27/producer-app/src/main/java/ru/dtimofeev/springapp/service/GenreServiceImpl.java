package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;
import ru.dtimofeev.springapp.rest.dto.GenreDto;
import ru.dtimofeev.springapp.rest.dto.mapping.GenreMapping;
import ru.dtimofeev.springapp.rest.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private static final String KAFKA_TOPIC = "Genres";
    private final GenreRepository genreRepository;
    private final GenreMapping genreMapping;
    private final String objectName = "Genre";
    private final KafkaTemplate<Long, Genre> kafkaTemplate;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapping genreMapping, KafkaTemplate<Long, Genre> kafkaTemplate) {
        this.genreRepository = genreRepository;
        this.genreMapping = genreMapping;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public GenreDto getById(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(objectName, "id", id));
        return genreMapping.toDto(genre);
    }

    @Override
    public List<GenreDto> getAll() {
        return genreRepository.findAll().stream().map(genre -> genreMapping.toDto(genre)).collect(Collectors.toList());
    }

    @Override
    public GenreDto getByName(String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException(objectName, "name", name));
        return genreMapping.toDto(genre);
    }

    @Override
    public GenreDto update(long id, GenreDto genredto) {
        Genre g = genreRepository.save(genreMapping.toEntity(genredto));
        return genreMapping.toDto(g);
    }

    @Override
    public GenreDto save(GenreDto genredto) {
        Genre g = genreRepository.save(genreMapping.toEntity(genredto));
        kafkaTemplate.send(KAFKA_TOPIC, g.getId(), g);
        return genreMapping.toDto(g);
    }

    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
