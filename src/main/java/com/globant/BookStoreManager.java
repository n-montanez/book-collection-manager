package com.globant;

import com.globant.model.base.Author;
import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import com.globant.model.dao.AuthorDAO;
import com.globant.model.dao.BookDAO;

import java.util.List;

public class BookStoreManager {
    public static void printAuthorList(AuthorDAO authorDAO) {
        System.out.println("------------------------------------");
        System.out.println("All available authors: ");
        for (Author author : authorDAO.findAll()) {
            System.out.println(author.getAuthorId() + " - " + author.getName());
        }
        System.out.println("------------------------------------");
    }

    public static void printBookList(BookDAO bookDAO) {
        System.out.println("------------------------------------");
        System.out.println("All available books: ");
        for (Book book : bookDAO.findAll()) {
            System.out.println(book.getId() + " - " + book.getTitle());
        }
        System.out.println("------------------------------------");
    }

    public static void printBookDetails(BookDAO bookDAO, long id) {
        Book book = bookDAO.findById(id);
        if (book == null) {
            System.out.println("Book " + id + " was not found");
            return;
        }
        System.out.println("------------------------------------");
        System.out.println(book.getTitle());
        System.out.println("Written by: " + book.getAuthor().getName());
        System.out.println("Release year: " + book.getYear());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("------------------------------------");
    }

    public static void printAvailableGenres() {
        System.out.println("------------------------------------");
        System.out.println("Available genres: ");
        for (Genre genre : Genre.values()) {
            System.out.println(genre.toString());
        }
        System.out.println("------------------------------------");
    }

    public static void booksByAuthor(BookDAO bookDAO, String authorName) {
        List<Book> books = bookDAO.findByAuthorName(authorName);

        if (books.isEmpty()) {
            System.out.println("--------------");
            System.out.println("No books found");
            System.out.println("--------------");
            return;
        }

        for (Book book : books) {
            System.out.println("------------------------------------");
            System.out.println(book.getTitle());
            System.out.println("Written by: " + book.getAuthor().getName());
            System.out.println("Release year: " + book.getYear());
            System.out.println("Genre: " + book.getGenre());
        }
        System.out.println("------------------------------------");
    }

    public static void booksByGenre(BookDAO bookDAO, String genre) {
        try {
            Genre genreValue = Genre.valueOf(genre.toUpperCase());

            List<Book> books = bookDAO.findByGenre(genreValue);

            if (books.isEmpty()) {
                System.out.println("--------------");
                System.out.println("No books found");
                System.out.println("--------------");
                return;
            }

            for (Book book : books) {
                System.out.println("------------------------------------");
                System.out.println(book.getTitle());
                System.out.println("Written by: " + book.getAuthor().getName());
                System.out.println("Release year: " + book.getYear());
                System.out.println("Genre: " + book.getGenre());
            }
            System.out.println("------------------------------------");
        } catch (IllegalArgumentException ex) {
            System.out.println("-------------");
            System.out.println("Invalid genre");
            System.out.println("-------------");
        }
    }

    public static void booksByTitle(BookDAO bookDAO, String title) {
        List<Book> books = bookDAO.findByTitle(title);

        if (books.isEmpty()) {
            System.out.println("--------------");
            System.out.println("No books found");
            System.out.println("--------------");
            return;
        }

        for (Book book : books) {
            System.out.println("------------------------------------");
            System.out.println(book.getTitle());
            System.out.println("Written by: " + book.getAuthor().getName());
            System.out.println("Release year: " + book.getYear());
            System.out.println("Genre: " + book.getGenre());
        }
        System.out.println("------------------------------------");
    }
}
