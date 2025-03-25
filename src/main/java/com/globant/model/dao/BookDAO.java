package com.globant.model.dao;

import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import com.globant.model.dao.generic.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookDAO extends GenericDaoImpl<Book> {
    public BookDAO(EntityManager entityManager) {
        super(Book.class, entityManager);
    }

    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<Book> byAuthor = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.author.name ILIKE %:authorName%",
                Book.class
        );
        byAuthor.setParameter("authorName", authorName);
        return byAuthor.getResultList();
    }

    public List<Book> findByGenre(Genre genre) {
        TypedQuery<Book> byGenre = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.genre ILIKE :genre",
                Book.class
        );
        byGenre.setParameter("genre", genre);
        return byGenre.getResultList();
    }
}
