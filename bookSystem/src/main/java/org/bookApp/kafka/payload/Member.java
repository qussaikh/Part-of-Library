package org.bookApp.kafka.payload;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "borrow", referencedColumnName = "id")
    private List<Book> book = new ArrayList<Book>();

    // Standardkonstruktor
    public Member() {
    }

    // Konstruktor för att skapa ett medlemsobjekt med specificerade attributvärden.
    public Member(String type, String firstName, String lastName, String gender, String email) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    // Getter och setter för alla attribut, inklusive böcker.
    // Getter och setter används för att få och sätta värden för objektets egenskaper.
    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Överskuggad toString()-metod för att skapa en strängrepresentation av medlemsobjektet.
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
