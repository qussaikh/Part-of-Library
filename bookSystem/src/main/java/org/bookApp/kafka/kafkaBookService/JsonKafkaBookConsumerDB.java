package org.bookApp.kafka.kafkaBookService;

import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonKafkaBookConsumerDB {

    // En statisk referens till BookRepository för att användas i de olika metoderna.
    public static BookRepository bookRepository;

    @Autowired
    public JsonKafkaBookConsumerDB(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // KafkaListener-annoterad metod som konsumerar meddelanden från Kafka-ämnet "Books".
    // Gruppen "library" används för att hantera meddelanden i en konsumentgrupp.
    @KafkaListener(topics = "Books", groupId = "libraryDB")
    public void writeBookToDb(Book book) {
        System.out.println(book);
        System.out.println("Skickar book data till DB!");
        // Skickar data till en databas genom att använda BookRepository för att spara boken.
        bookRepository.save(book);
    }

    // Läser en bok från databasen med hjälp av ett id.
    public Book readFromDb(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Uppdaterar en bok i databasen.
    public Book updateInDb(Book updatedOrder) {
        return bookRepository.save(updatedOrder);
    }

    // Ta bort en bok från databasen med hjälp av id
    public static void  deleteFromDb(int id) {
        bookRepository.deleteById(id);
    }

}
