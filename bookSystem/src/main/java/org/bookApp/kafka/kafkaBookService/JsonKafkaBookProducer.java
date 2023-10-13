package org.bookApp.kafka.kafkaBookService;

import org.bookApp.kafka.payload.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaBookProducer {

    private KafkaTemplate<String, Book> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger((JsonKafkaBookProducer.class));

    // Konstruktor för Kafka-producentklassen som tar emot en Kafka-templatinstans.
    public JsonKafkaBookProducer(KafkaTemplate<String, Book> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Metod för att skicka en bok som meddelande till ett Kafka-ämne.
    public void sendMessage(Book data) {
        LOGGER.info(String.format("Message sent -> %s", data.toString()));// Loggar meddelandet.
        // Skapar ett Kafka-meddelande med boken som innehåll och Kafka-ämnet som header.
        Message<Book> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "Books").build();
        // Skickar meddelandet till Kafka-ämnet med hjälp av Kafka-templatet.
        kafkaTemplate.send(message);
    }
}
