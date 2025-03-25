package com.globant.model.dao;

import com.globant.model.base.Book;
import com.globant.model.dao.generic.GenericDaoImpl;
import jakarta.persistence.EntityManager;

public class BookDAO extends GenericDaoImpl<Book> {
    public BookDAO(EntityManager entityManager) {
        super(Book.class, entityManager);
    }
}
