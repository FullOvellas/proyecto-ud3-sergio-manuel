package com.vaultapp.model.repository;


import com.vaultapp.model.entities.Book;
import com.vaultapp.model.pojo.book.dao.BookApiDao;

import java.util.List;

public class BookApiRepository {
    private static BookApiRepository instance;

    static {
        instance = new BookApiRepository();
    }

    public static BookApiRepository getInstance() {
        return instance;
    }

    /**
     * Gets the list of books in relation of the provided title.
     * @param title of the book.
     * @return list of books.
     */
    public List<Book> getAsList(String title) {
        return BookApiDao.getInstance().read(title);
    }
}
