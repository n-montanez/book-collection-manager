package com.globant.model.dao;

import com.globant.model.base.Author;
import com.globant.model.base.Book;
import com.globant.model.base.Genre;
import com.globant.model.dao.generic.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends GenericDaoImpl<Book> {
    public BookDAO(EntityManager entityManager) {
        super(Book.class, entityManager);
    }

    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<Book> byAuthor = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.author.name ILIKE :authorName",
                Book.class
        );
        byAuthor.setParameter("authorName", "%" + authorName + "%");
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

    public List<Book> findByTitle(String title) {
        TypedQuery<Book> byTitle = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.title ILIKE :title",
                Book.class
        );
        byTitle.setParameter("title", "%" + title + "%");
        return byTitle.getResultList();
    }

    public List<Book> search(String authorName, String bookTitle, Genre genre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> bookRoot = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (authorName != null && !authorName.isEmpty()) {
            Join<Book, Author> author = bookRoot.join("author");
            predicates.add(cb.like(cb.lower(author.get("name")), "%" + authorName.toLowerCase() + "%"));
        }
        if (bookTitle != null && !bookTitle.isEmpty()) {
            predicates.add(cb.like(bookRoot.get("title"), "%" + bookTitle + "%"));
        }
        if (genre != null) {
            predicates.add(cb.equal(bookRoot.get("genre"), genre));
        }

        query.select(bookRoot).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }
}
