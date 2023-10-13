package org.bookApp.kafka.Controller;

import org.bookApp.kafka.kafkaMemberService.JsonKafkaMemberConsumerDB;
import org.bookApp.kafka.kafkaMemberService.JsonKafkaMemberProducer;
import org.bookApp.kafka.payload.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class jsonMessageMemberController {
    // Konstruktor för kontrollerklassen som tar emot en Kafka-producer
    private JsonKafkaMemberProducer kafkaMemberProducer;


    public jsonMessageMemberController(JsonKafkaMemberProducer kafkaMemberProducer) {
        this.kafkaMemberProducer = kafkaMemberProducer;

    }
    // En POST-metod som tar emot ett medlem som JSON i förfrågan och publicerar den till Kafka-ämnet.
    @PostMapping("/publishMember")
    public ResponseEntity<String> publishMember(@RequestBody Member member){
        kafkaMemberProducer.sendMessage(member); // Anropa Kafka-producenten för att skicka meddelandet till Kafka-ämnet.
        return ResponseEntity.ok("Json member Message send to Kafka Topic"); // Returnera ett svar med en bekräftelse.
    }

}
