package org.bookApp.kafka.kafkaMemberService;

import org.bookApp.kafka.payload.Member;
import org.bookApp.kafka.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaMemberConsumer {
    //  logger (SLF4J) som är instansiering i klassen för att logga meddelanden.
    private static final Logger LOGGER1 = LoggerFactory.getLogger(JsonKafkaMemberConsumer.class);

    // logger (SLF4J) som är instansiering i klassen för att logga meddelanden.
    @Autowired
    private MemberRepository memberRepository;

    // KafkaListener-annoterad metod som konsumerar meddelanden från Kafka-ämnet "javaJsonMember".
    // Gruppen "library" används för att hantera meddelanden i en konsumentgrupp.
    @KafkaListener(topics = "Members", groupId = "library")
    public void consumeMember(Member member) {
        // Loggar det mottagna JSON-meddelandet.
        LOGGER1.info(String.format("Json message recieved -> %s", member.toString()));
    }

}
