package com.globant.utils;

import com.globant.model.base.Author;
import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import com.globant.model.dao.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@Slf4j
public class BookDaoMockedTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    TypedQuery<Book> typedQuery;

    @InjectMocks
    private BookDAO bookDAO;

    private List<Book> mockBooks;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        Author rowling = new Author(1L, "J.K. Rowling", new ArrayList<>());
        Author king = new Author(2L, "Stephen King", new ArrayList<>());

        mockBooks = Arrays.asList(
                new Book(1L, "Harry Potter and the Philosopher's stone", 1997, rowling, Genre.FANTASY),
                new Book(2L, "Harry Potter and the Chamber of Secrets", 1998, rowling, Genre.FANTASY),
                new Book(3L, "Harry Potter and the Prisoner of Azkaban", 1999, rowling, Genre.FANTASY),
                new Book(4L, "The Casual Vacancy", 2012, rowling, Genre.MYSTERY),
                new Book(5L, "The shining", 1977, king, Genre.HORROR),
                new Book(6L, "Carrie", 1974, king, Genre.HORROR)
        );
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testFindByAuthorName() {
        when(entityManager.createQuery(anyString(), eq(Book.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("authorName"), anyString())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(
                mockBooks.stream().filter(b -> "J.K. Rowling".equals(b.getAuthor().getName())).toList()
        );

        List<Book> books = bookDAO.findByAuthorName("rOwLIng");

        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(4, books.size());

        List<String> expectedTitles = Arrays.asList(
                "Harry Potter and the Philosopher's stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "The Casual Vacancy"
        );

        Assertions.assertTrue(expectedTitles.containsAll(TestUtils.getBookTitles(books)));
    }
}
