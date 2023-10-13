package org.bookApp.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    // Denna klass definierar Kafka-ämnen som används i applikationen.

    // Denna metod skapar ett Kafka-ämne med namnet "Books".
    @Bean
    public NewTopic myJsonTopic()
    { return TopicBuilder.name("Books").partitions(2).build(); }

    // Denna metod skapar ett annat Kafka-ämne med namnet "Members".
    @Bean
    public NewTopic myJsonMember(){
        return TopicBuilder.name("Members").partitions(2).build();
    }
}