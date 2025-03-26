package com.globant.utils;

import com.globant.model.base.Book;
import java.util.List;

public class TestUtils {
    public static List<String> getBookTitles(List<Book> books) {
        return books.stream().map(Book::getTitle).toList();
    }
}
