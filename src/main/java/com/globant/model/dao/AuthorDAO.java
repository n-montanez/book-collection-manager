package com.globant.model.dao;

import com.globant.model.base.Author;
import com.globant.model.dao.generic.GenericDaoImpl;

public class AuthorDAO extends GenericDaoImpl<Author> {
    public AuthorDAO() {
        super(Author.class);
    }
}
