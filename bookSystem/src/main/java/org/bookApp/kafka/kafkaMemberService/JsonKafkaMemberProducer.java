package org.bookApp.kafka.kafkaMemberService;

import org.bookApp.kafka.payload.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class JsonKafkaMemberProducer {

    private KafkaTemplate<String, Member> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger((JsonKafkaMemberProducer.class));

    // Konstruktor för Kafka-producentklassen som tar emot en Kafka-templatinstans.
    public JsonKafkaMemberProducer(KafkaTemplate<String, Member> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Metod för att skicka medlem som meddelande till ett Kafka-ämne.
    public void sendMessage(Member data) {
        LOGGER.info(String.format("Message sent -> %s", data.toString()));// Loggar meddelandet.
        // Skapar ett Kafka-meddelande med medlem som innehåll och Kafka-ämnet som header.
        Message<Member> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "Members").build();
        // Skickar meddelandet till Kafka-ämnet med hjälp av Kafka-templatet.
        kafkaTemplate.send(message);
    }

}
