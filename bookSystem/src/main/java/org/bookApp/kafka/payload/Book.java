package org.bookApp.kafka.payload;

import jakarta.persistence.*;


// En JPA-entitetsklass som representerar böcker och deras attribut i en databastabell.
@Entity
@Table(name="MyBookStore")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String price;


    // Standardkonstruktor för JPA.
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }
    // Konstruktor för att skapa en bok med titel, författare och pris.
    public Book(String title, String author, String price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter- och setter-metoder för att få och sätta egenskaper för en bok.
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }


    // En överskuggad toString() -metod för att skapa en strängrepresentation av boken.
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
