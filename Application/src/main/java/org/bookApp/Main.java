package org.bookApp;

import org.bookApp.kafka.payload.Member;
import java.net.MalformedURLException;

import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException, org.json.simple.parser.ParseException {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);
        LibraryManager libraryManager = new LibraryManager();
        int choice;

        do {
            // Clear konsollen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Menu
            System.out.println("Välkommen till menu:");
            System.out.println("1. lägg till bok ");
            System.out.println("2. lägg till medlem ");
            System.out.println("3. Vissa alla Bockar från Kafka");
            System.out.println("4. Vissa alla medlemmar från Kafka");

            System.out.print("välja alternative  : ");


            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    LibraryManager.addBookToWebAPI();
                    break;
                case 2:
                    LibraryManager.addMemberToWebAPI();
                    break;
                case 3:
                    LibraryManager.getBookFromKafka("Books");
                    break;
                case 4:
                    LibraryManager.getMemberFromKafka("Members");
                    break;
                default:
                    System.out.println("Ugiltigt val. Prov igen.");
                    break;
            }

            System.out.print("\nTryck på Enter for at fortsatte...");
            scanner.nextLine();
            scanner.nextLine();

        } while (choice <= 5);

        scanner.close();

    }
}
