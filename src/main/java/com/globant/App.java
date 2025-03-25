package com.globant;

import com.globant.model.dao.AuthorDAO;
import com.globant.model.dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("-------------------------");
        System.out.println("Welcome to the Book Store");
        System.out.println("-------------------------");

        try (Scanner scanner = new Scanner(System.in);
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPU");
             EntityManager em = emf.createEntityManager()) {

            BookDAO bookDAO = new BookDAO(em);
            AuthorDAO authorDAO = new AuthorDAO(em);

            do {
                printMenu();
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("All available books: ");
                        System.out.println(bookDAO.findAll());
                        break;
                    case 2:
                        System.out.println("All available authors: ");
                        System.out.println(authorDAO.findAll());
                        break;
                    case 3:
                        em.close();
                        emf.close();
                        scanner.close();
                        System.exit(0);
                }
            } while (true);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("1. List books");
        System.out.println("2. List authors");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
    }
}
