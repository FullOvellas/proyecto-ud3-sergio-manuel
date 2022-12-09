package com.vaultapp.controller;

import com.vaultapp.model.repository.BookDbRepository;
import com.vaultapp.view.cli.BookCliView;

public class BookController {
    private BookDbRepository bookRepository;
    private BookCliView bookCliView;

    public BookController() {
        bookRepository = BookDbRepository.getInstance();
        bookCliView = new BookCliView();
    }

    public BookDbRepository getBookRepository() {
        return bookRepository;
    }

    public BookCliView getBookCliView() {
        return bookCliView;
    }
}
