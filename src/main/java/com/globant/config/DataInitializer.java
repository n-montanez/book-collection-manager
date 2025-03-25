package com.globant.config;

import com.globant.model.base.Author;
import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import jakarta.persistence.EntityManager;

import javax.annotation.PostConstruct;

public class DataInitializer {
    private final EntityManager entityManager;

    public DataInitializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void init() {
        entityManager.getTransaction().begin();

        Author rowling = Author.builder()
                .name("J.K. Rowling")
                .build();

        Author murakami = Author.builder()
                .name("Haruki Murakami")
                .build();

        entityManager.persist(rowling);
        entityManager.persist(murakami);

        Book harryPotter = Book.builder()
                .title("Harry Potter and the Philosopher's stone")
                .year(1997)
                .genre(Genre.FANTASY)
                .author(rowling)
                .build();

        Book norway = Book.builder()
                .title("Norwegian wood (ノルウェイの森)")
                .year(1987)
                .genre(Genre.NON_FICTION)
                .author(murakami)
                .build();

        entityManager.persist(harryPotter);
        entityManager.persist(norway);

        entityManager.getTransaction().commit();
    }
}
