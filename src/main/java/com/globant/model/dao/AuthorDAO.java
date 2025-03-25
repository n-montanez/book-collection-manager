package com.globant.model.dao;

import com.globant.model.base.Author;
import com.globant.model.dao.generic.GenericDaoImpl;
import jakarta.persistence.EntityManager;


public class AuthorDAO extends GenericDaoImpl<Author> {
    public AuthorDAO(EntityManager entityManager) {
        super(Author.class, entityManager);
    }
}
