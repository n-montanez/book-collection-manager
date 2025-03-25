package com.globant;

import com.globant.config.DataInitializer;
import com.globant.model.dao.AuthorDAO;
import com.globant.model.dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

import static com.globant.BookStoreManager.*;

@Slf4j
public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPU");
             EntityManager em = emf.createEntityManager()) {

            new DataInitializer(em).init();

            BookDAO bookDAO = new BookDAO(em);
            AuthorDAO authorDAO = new AuthorDAO(em);

            System.out.println("-------------------------");
            System.out.println("Welcome to the Book Store");
            System.out.println("-------------------------");

            do {
                printMenu();
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        printBookList(bookDAO);
                        break;
                    case 2:
                        printAuthorList(authorDAO);
                        break;
                    case 3:
                        printBookList(bookDAO);
                        System.out.print("Enter book id: ");
                        long id = scanner.nextLong();
                        printBookDetails(bookDAO, id);
                        break;
                    case 4:
                        System.out.print("Enter author name: ");
                        String authorName = scanner.nextLine();
                        booksByAuthor(bookDAO, authorName);
                        break;
                    case 5:
                        printAvailableGenres();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        booksByGenre(bookDAO, genre);
                        break;
                    case 6:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        booksByTitle(bookDAO, title);
                        break;
                    case 7:
                        em.close();
                        emf.close();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("-----------------");
                        System.out.println("Invalid option!!!");
                        System.out.println("-----------------");
                        break;
                }
            } while (true);
        } catch (PersistenceException ex) {
            log.error("Persistence Unit Error: {}", ex.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("1. List books");
        System.out.println("2. List authors");
        System.out.println("3. Show book details");
        System.out.println("4. Find books by author");
        System.out.println("5. Find books by genre");
        System.out.println("6. Find books by title");
        System.out.println("7. Exit");
        System.out.print("Enter option: ");
    }
}
