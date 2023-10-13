package org.bookApp.kafka;

import org.bookApp.kafka.payload.Book;
import org.bookApp.kafka.payload.Member;
import org.bookApp.kafka.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseMemberTest {
    @Autowired
    MemberRepository memberRepository;

    static Member testMember;

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
    void createMember(){
        //Skapa ett objekt av member med specifik data
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("first name  member test 1");
        member.setLastName("last name member test");
        member.setType("test type");
        member.setGender("male");
        member.setEmail("test@mail.com");


        //Spara member till DB
        testMember = memberRepository.save(member);

        assertNotNull(memberRepository.findById(Math.toIntExact(testMember.getId())).get().getFirstName());

        System.out.println(testMember.getId());

    }

    @Test
    @Order(2)
    void updateMember() {
        //Hämta member med id 1
        Member fetchedMember = memberRepository.findById(Math.toIntExact(testMember.getId())).get();
        assertNotNull(fetchedMember);

        //Updatera värdet i fetchedmember
        fetchedMember.setFirstName("first name member test 1");
        memberRepository.save(fetchedMember);
        assertEquals("first name member test 1", memberRepository.findById(Math.toIntExact(testMember.getId())).get().getFirstName());
    }

    @Test
    @Order(3)
    void removeMember() {
        //Kontrollera att member med ID 1 finns
        assertNotNull(memberRepository.findById(Math.toIntExact(testMember.getId())).get());

        //Ta bort member med ID 1 och kontroller att member är borta
        memberRepository.deleteById(Math.toIntExact(testMember.getId()));
        assertTrue(memberRepository.findById(Math.toIntExact(testMember.getId())).isEmpty());
    }


}
