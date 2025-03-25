package com.globant;

import com.globant.config.DataInitializer;
import com.globant.model.base.Author;
import com.globant.model.base.Book;
import com.globant.model.dao.AuthorDAO;
import com.globant.model.dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

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

                switch (option) {
                    case 1:
                        System.out.println("All available books: ");
                        for (Book book : bookDAO.findAll()) {
                            System.out.println(book.toString());
                        }
                        break;
                    case 2:
                        System.out.println("All available authors: ");
                        for (Author author : authorDAO.findAll()) {
                            System.out.println(author.toString());
                        }
                        break;
                    case 3:
                        em.close();
                        emf.close();
                        scanner.close();
                        System.exit(0);
                }
            } while (true);
        } catch (PersistenceException ex) {
            log.error("Persistence Unit Error: {}", ex.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("1. List books");
        System.out.println("2. List authors");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
    }
}
