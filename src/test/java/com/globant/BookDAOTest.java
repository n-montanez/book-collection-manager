package com.globant;

import com.globant.model.base.Book;
import com.globant.model.dao.BookDAO;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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

        List<String> titles = new ArrayList<>();

        for (Book book : books) {
            Assertions.assertEquals("J.K. Rowling", book.getAuthor().getName());
            titles.add(book.getTitle());
        }

        List<String> expectedTitles = Arrays.asList(
                "Harry Potter and the Philosopher's stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "The Casual Vacancy"
        );

        Assertions.assertTrue(expectedTitles.containsAll(titles));
    }

}
