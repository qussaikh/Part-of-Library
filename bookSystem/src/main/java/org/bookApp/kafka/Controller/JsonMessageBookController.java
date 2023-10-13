package org.bookApp.kafka.Controller;

import org.bookApp.kafka.kafkaBookService.JsonKafkaBookConsumerDB;
import org.bookApp.kafka.kafkaBookService.JsonKafkaBookProducer;
import org.bookApp.kafka.payload.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageBookController {
    // Konstruktor för kontrollerklassen som tar emot en Kafka-producer
    private JsonKafkaBookProducer kafkaBookProducer;


    public JsonMessageBookController(JsonKafkaBookProducer kafkaProducer) {
        this.kafkaBookProducer = kafkaProducer;
    }

    // En POST-metod som tar emot en bok som JSON i förfrågan och publicerar den till Kafka-ämnet.
    @PostMapping("/publishBook")
    public ResponseEntity<String> publishBook(@RequestBody Book book) {
        kafkaBookProducer.sendMessage(book); // Anropa Kafka-producenten för att skicka meddelandet till Kafka-ämnet.
        return ResponseEntity.ok("Json book Message send to Kafka Topic"); // Returnera ett svar med en bekräftelse.
    }


}

