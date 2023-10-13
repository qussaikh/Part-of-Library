package org.bookApp.kafka;

import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseBookTest {
    @Autowired
    BookRepository bookRepository;

    static Book testBook;

    @BeforeEach
    void setUp() {
        System.out.println("Befor Test");
    }

    @AfterEach
    void tearDown(){
        System.out.println("After Test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Alla test avslutade!");
    }


    @Test
    @Order(1)
    void createBook(){
        //Skapa ett objekt av book med specifik data
        Book book = new Book();
        book.setId(1);
        book.setTitle("test book 1");
        book.setAuthor("test author 1");
        book.setPrice("1");


        //Spara Book till DB
        testBook = bookRepository.save(book);

        assertNotNull(bookRepository.findById(testBook.getId()).get().getTitle());

        System.out.println(testBook.getId());

    }

    @Test
    @Order(2)
    void updateBook() {
        //Hämta book med id 1
        Book fetchedbook = bookRepository.findById(testBook.getId()).get();
        assertNotNull(fetchedbook);

        //Updatera värdet i fetchedBook
        fetchedbook.setTitle("Test book 2");
        bookRepository.save(fetchedbook);
        assertEquals("Test book 2", bookRepository.findById(testBook.getId()).get().getTitle());
    }

    @Test
    @Order(3)
    void removeBook() {
        //Kontrollera att book med ID 1 finns
        assertNotNull(bookRepository.findById(testBook.getId()).get());

        //Ta bort book med ID 1 och kontroller att book är borta
        bookRepository.deleteById(testBook.getId());
        assertTrue(bookRepository.findById(testBook.getId()).isEmpty());
    }



}
