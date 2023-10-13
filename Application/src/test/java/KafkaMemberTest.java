import org.bookApp.LibraryManager;
import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.payload.Member;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KafkaMemberTest {

    private static Member member;

    private static LibraryManager libraryManager;

    private static JSONObject jsonObject; // Deklarerar en JSON-objekt som används för testning.

    @BeforeAll
    static void beforAll(){ // Metod som körs innan alla testfall. Används för att förbereda testdata.
        member = new Member();
        member.setFirstName(" Kafka first memberTest");
        member.setLastName("Kafka last memberTest");
        member.setType("Action test");
        member.setGender("30");
        member.setEmail("test@mail.com");


        jsonObject = new JSONObject(); // Skapar ett JSON-objekt.
        jsonObject.put("firstName",member.getFirstName());
        jsonObject.put("lastName", member.getLastName());
        jsonObject.put("type", member.getType());
        jsonObject.put("gender", member.getGender());
        jsonObject.put("email", member.getEmail());


    }

    @Test
    @Order(1)
    public void sendToApi(){ // Testfall för att skicka data till en webb-API.
        String resp = libraryManager.sendMemberToWebAPI(jsonObject);  // Skickar JSON-data till API och lagrar svaret.
        assertEquals(resp, "Json member Message send to Kafka Topic");// Hämtar den senaste boken i listan.

    }

    @Test
    @Order(2)
    public void getDataFromKafka() { // Testfall för att hämta data från Kafka.
        ArrayList<Member> members = libraryManager.getMemberFromKafka("Members"); // Hämtar memberdata från Kafka
        Member testMember = members.get(members.size() - 1); // Hämtar den senaste member i listan.

        assertEquals(testMember.getFirstName(), member.getFirstName());
        assertEquals(testMember.getLastName(), member.getLastName());
        assertEquals(testMember.getType(), member.getType());
        assertEquals(testMember.getGender(), member.getGender());
        assertEquals(testMember.getEmail(), member.getEmail());
    }
}
