import org.bookApp.LibraryManager;
import org.bookApp.Main;
import org.bookApp.kafka.payload.Book;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KafkaTests {
    private static Book book; // Deklarerar en bok som används för testning.

    private static LibraryManager libraryManager;
    
    private static JSONObject jsonObject; // Deklarerar en JSON-objekt som används för testning.
    
    @BeforeAll
    static void beforAll(){ // Metod som körs innan alla testfall. Används för att förbereda testdata.
        book = new Book(); // Skapar en ny bok.
        book.setTitle("Kafka testbok "); // Anger bokens titel.
        book.setAuthor("Testförfattare"); // Anger författaren till boken.
        book.setPrice("299"); // Anger priset på boken.

        jsonObject = new JSONObject(); // Skapar ett JSON-objekt.
        jsonObject.put("title",book.getTitle()); // Lägger till bokens titel i JSON-objektet.
        jsonObject.put("author", book.getAuthor()); // Lägger till författaren i JSON-objektet.
        jsonObject.put("price", book.getPrice()); // Lägger till priset i JSON-objektet.
        
    }
    
    @Test
    @Order(1)
    public void sendToApi(){ // Testfall för att skicka data till en webb-API.
        String resp = libraryManager.sendToWebAPI(jsonObject);  // Skickar JSON-data till API och lagrar svaret.
        assertEquals(resp, "Json book Message send to Kafka Topic");// Hämtar den senaste boken i listan.

    }

    @Test
    @Order(2)
    public void getDataFromKafka(){ // Testfall för att hämta data från Kafka.
        ArrayList<Book> books = libraryManager.getBookFromKafka("BooksTop"); // Hämtar bokdata från Kafka
        Book testBook = books.get(books.size() - 1); // Hämtar den senaste boken i listan.

        assertEquals(testBook.getTitle(), book.getTitle()); // Jämför titeln på den hämtade boken med den förväntade.
        assertEquals(testBook.getAuthor(), book.getAuthor()); // Jämför författaren på den hämtade boken med den förväntade.
        assertEquals(testBook.getPrice(), book.getPrice()); // Jämför priset på den hämtade boken med det förväntade.

    }

}
