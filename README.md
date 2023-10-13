# Kafka-spring-boot-part-of-a-library

### A Spring boot, REST API, kafka and mysql project.
Maven application and a MySQL database useing Kafka. The project is part of a library application that manages book and member information

## Table of Contents
- [Description](#Description)
- [Installation and Usage](#Installation-and-Usage)
- [Dependencies](#Dependencies)

## Description
The Kafka BookStore project is a Java application that demonstrates the use of Apache Kafka for messaging and Spring Boot for building a RESTful API. The application focuses on managing and processing book and member data, allowing users to publish and consume data via Kafka topics.

## Installation and Usage
To install and run the Kafka BookApp, follow these steps:
- Prerequisites:
    - Make sure you have Java and Maven installed on your system.
    - Set up Apache Kafka and create the necessary topics ("Books" and "Members").
- Clone the Repository
- Run the Application
    - Attention:
        - To use the application you need to have a local MySQL database running on your computer.
        - start your Zookeeper and Kafka servers (Brokers), information on how to do this for your system can be found on the Kafka homepage.
        - its very important that you start them before you start the applications, or else the applications you will get an error that the replication factor don't have enough brokers.
- API Endpoints:
    - The application exposes the following API endpoints:
        - Publish a book to Kafka:
          'POST /api/v1/kafka/publishBook' - Send a JSON book object to Kafka.
        - Publish a member to Kafka:
          'POST /api/v1/kafka/publishMember' - Send a JSON member object to Kafka.
- Kafka Consumers:
  The application includes Kafka consumers to process and store data in a database.
- Database Configuration:
  Configure the database connection in the 'application.properties' file.

## Dependencies
The Kafka BookStore project relies on the following libraries and frameworks:
- Spring Boot: Provides the core framework for building the application.
- Apache Kafka: Used for message queuing and topic-based communication.
- Spring Data JPA: Simplifies data access and database operations.
- H2 Database: An embedded database used for storage.
- Maven: Used for project build and dependency management.

Make sure to have these dependencies properly installed and configured to run the Kafka BookStore successfully.

### Note:
This readme assumes you have a basic understanding of Kafka, Spring Boot, and database configuration. Please ensure that you have set up Kafka, including creating the necessary topics, before running the application.










