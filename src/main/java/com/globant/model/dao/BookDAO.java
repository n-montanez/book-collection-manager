package com.globant.model.dao;

import com.globant.model.base.Book;
import com.globant.model.dao.generic.GenericDaoImpl;

public class BookDAO extends GenericDaoImpl<Book> {
    public BookDAO() {
        super(Book.class);
    }
}
