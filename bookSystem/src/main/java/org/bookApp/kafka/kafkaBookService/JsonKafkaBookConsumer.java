package org.bookApp.kafka.kafkaBookService;

import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaBookConsumer {
    //  logger (SLF4J) som är instansiering i klassen för att logga meddelanden.
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JsonKafkaBookConsumer.class);

    // logger (SLF4J) som är instansiering i klassen för att logga meddelanden.
    @Autowired
    private BookRepository bookRepository;

    // KafkaListener-annoterad metod som konsumerar meddelanden från Kafka-ämnet "javaJsonBooks".
    // Gruppen "library" används för att hantera meddelanden i en konsumentgrupp.
    @KafkaListener(topics = "Books", groupId = "library")
    public void consumeBook(Book book) {
        // Loggar det mottagna JSON-meddelandet.
        LOGGER.info(String.format("Json message recieved -> %s", book.toString()));
    }
}
