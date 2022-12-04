package com.vaultapp.controller;

import com.vaultapp.model.repository.BookRepository;
import com.vaultapp.view.cli.BookCliView;

public class BookController {
    private BookRepository bookRepository;
    private BookCliView bookCliView;

    public BookController() {
        bookRepository = BookRepository.getInstance();
        bookCliView = new BookCliView();
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookCliView getBookCliView() {
        return bookCliView;
    }
}
