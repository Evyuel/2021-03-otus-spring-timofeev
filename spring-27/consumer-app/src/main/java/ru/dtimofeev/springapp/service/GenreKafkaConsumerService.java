package ru.dtimofeev.springapp.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import ru.dtimofeev.springapp.models.Genre;

public interface GenreKafkaConsumerService {
    @KafkaListener(topics = {"INPUT_DATA"})
    void consume(@Payload Genre message,
                 @Header(KafkaHeaders.OFFSET) Integer offset,
                 @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                 @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                 Acknowledgment acknowledgment
    );
}
