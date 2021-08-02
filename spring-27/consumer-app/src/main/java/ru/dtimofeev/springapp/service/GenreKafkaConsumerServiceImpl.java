package ru.dtimofeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreRepository;

@Service
public class GenreKafkaConsumerServiceImpl implements GenreKafkaConsumerService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreKafkaConsumerServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @KafkaListener(topics = {"Genres"})
    @Override
    public void consume(final @Payload Genre message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment
    ) {
        genreRepository.save(message);
        acknowledgment.acknowledge();
    }
}
