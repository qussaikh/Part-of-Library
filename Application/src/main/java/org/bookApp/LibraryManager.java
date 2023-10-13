package org.bookApp;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.payload.Member;
import org.json.simple.JSONObject;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class LibraryManager {

    public static void addBookToWebAPI() {
        Book book = new Book();
        System.out.println("Skriv in information för att skapa bok:");
        Scanner scanner = new Scanner(System.in);

        // Be användaren om nödvändig information
        System.out.print("Ange ett book Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        book.setId(id);

        System.out.print("Ange ett book namn: ");
        String name = scanner.nextLine();
        book.setTitle(name);

        System.out.print("Ange en Author: ");
        String author = scanner.nextLine();
        book.setAuthor(author);

        System.out.print("Ange Priset: ");
        String price = scanner.nextLine();
        book.setPrice(price);
        scanner.nextLine();

        JSONObject bookObj = new JSONObject();
        bookObj.put("id",book.getId());
        bookObj.put("title",book.getTitle());
        bookObj.put("author", book.getAuthor());
        bookObj.put("price", book.getPrice());

        //Skicka Payload till WebAPI via en Request
        sendToWebAPI(bookObj);
    }


    public static void addMemberToWebAPI() {
        Member member = new Member();
        System.out.println("Skriv in information för att skapa account :");
        Scanner scanner = new Scanner(System.in);

        // Be användaren om nödvändig information
        System.out.print("Ange ett för-namn: ");
        String name = scanner.nextLine();
        member.setFirstName(name);

        System.out.print("Ange ett after-namn: ");
        String lastname = scanner.nextLine();
        member.setLastName(lastname);

        System.out.print("Ange en type: ");
        String type = scanner.nextLine();
        member.setType(type);

        System.out.print("Ange gender: ");
        String gender = scanner.nextLine();
        member.setGender(gender);

        System.out.print("Ange mail adress: ");
        String mail = scanner.nextLine();
        member.setEmail(mail);

        JSONObject memberObj = new JSONObject();
        memberObj.put("type", member.getType());
        memberObj.put("firstName", member.getFirstName() );
        memberObj.put("lastName", member.getLastName() );
        memberObj.put("gender", member.getGender());
        memberObj.put("email", member.getEmail());

        //Skicka Payload till WebAPI via en Request
        sendMemberToWebAPI(memberObj);
    }

    public static String sendMemberToWebAPI(JSONObject myObj ) {
        String returnResp = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/kafka/publishMember");

            // Skapa en JSON-förfrågningskropp
            String jsonPayload = myObj.toJSONString();
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // Skicka förfrågan och hantera svaret
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    System.out.println("Svar från server: " + responseString);
                    returnResp = responseString;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return returnResp;
    }


    public static String sendToWebAPI(JSONObject myObj ) {
        String returnResp = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/kafka/publishBook");

            // Skapa en JSON-förfrågningskropp
            String jsonPayload = myObj.toJSONString();
            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // Skicka förfrågan och hantera svaret
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    System.out.println("Svar från server: " + responseString);
                    returnResp = responseString;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return returnResp;
    }


    public static ArrayList<Book> getBookFromKafka(String topicName) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fetchingBook");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        Consumer<String, Book> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Collections.singletonList(topicName));
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Create book list
        ArrayList<Book> books = new ArrayList<Book>();

        //WhileLoop som hämtar i JSON format
        while (true) {
            ConsumerRecords<String, Book> records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) continue;
            for (ConsumerRecord<String, Book> record : records) {
                books.add(record.value());
            }
            break;
        }

        for (Book book : books) {
            System.out.println(book.toString());
        }

        return books;
    }

    public static ArrayList<Member> getMemberFromKafka(String topicName) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fetchingMember");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        Consumer<String, Member> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Collections.singletonList(topicName));
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Create book list
        ArrayList<Member> members = new ArrayList<Member>();

        //WhileLoop som hämtar i JSON format
        while (true) {
            ConsumerRecords<String, Member> records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) continue;
            for (ConsumerRecord<String, Member> record : records) {
                members.add(record.value());
            }
            break;
        }

        for (Member member : members) {
            System.out.println(member.toString());
        }
        return members;
    }
}


