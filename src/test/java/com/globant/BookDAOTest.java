package com.globant;

import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import com.globant.model.dao.BookDAO;
import com.globant.utils.TestUtils;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class BookDAOTest {

    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookPU");
        bookDAO = new BookDAO(emf.createEntityManager());
    }

    @Test
    public void testFindByAuthorName() {
        List<Book> books = bookDAO.findByAuthorName("rOwLiNG");
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(4, books.size());

        for (Book book : books) {
            Assertions.assertEquals("J.K. Rowling", book.getAuthor().getName());
        }

        List<String> expectedTitles = Arrays.asList(
                "Harry Potter and the Philosopher's stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "The Casual Vacancy"
        );

        Assertions.assertTrue(expectedTitles.containsAll(TestUtils.getBookTitles(books)));
    }

    @Test
    public void testFindByBookTitle() {
        List<Book> books = bookDAO.findByTitle("hArry PoTTer");
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(3, books.size());

        List<String> expectedTitles = Arrays.asList(
                "Harry Potter and the Philosopher's stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban"
        );

        Assertions.assertTrue(expectedTitles.containsAll(TestUtils.getBookTitles(books)));
    }

    @Test
    public void testFindByGenre() {
        List<Book> books = bookDAO.findByGenre(Genre.MYSTERY);
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(2, books.size());

        List<String> expectedTitles = Arrays.asList(
                "The shining",
                "The Green Mile"
        );

        Assertions.assertTrue(expectedTitles.containsAll(TestUtils.getBookTitles(books)));
    }

    @Test
    public void testsFindEmptyGenre() {
        List<Book> books = bookDAO.findByGenre(Genre.SCIENCE);
        Assertions.assertTrue(books.isEmpty());
    }

}
