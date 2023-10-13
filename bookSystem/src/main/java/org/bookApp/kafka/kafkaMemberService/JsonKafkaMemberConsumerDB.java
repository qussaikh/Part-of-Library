package org.bookApp.kafka.kafkaMemberService;

import org.bookApp.kafka.payload.Member;
import org.bookApp.kafka.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaMemberConsumerDB {

    // En statisk referens till MemberRepository för att användas i de olika metoderna.
    public static MemberRepository memberRepository;

    @Autowired
    public JsonKafkaMemberConsumerDB (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // KafkaListener-annoterad metod som konsumerar meddelanden från Kafka-ämnet "Members".
    // Gruppen "library" används för att hantera meddelanden i en konsumentgrupp.
    @KafkaListener(topics = "Members", groupId = "libraryDB")
    public void writeMemberToDb(Member member) {
        System.out.println(member);
        System.out.println("Skickar member data till DB!");
        // Skickar data till en databas genom att använda BookRepository för att spara boken.
        memberRepository.save(member);
    }

    // Läsar medlem från databasen med hjälp av id
    public Member readFromDb(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    // Uppdatera medlem i databasen
    public Member updateInDb(Member updatedOrder) {
        return memberRepository.save(updatedOrder);
    }

    // Ta bort medlem från databasen med hjälp av id
    public static void  deleteFromDb(int id) {
        memberRepository.deleteById(id);
    }
}
