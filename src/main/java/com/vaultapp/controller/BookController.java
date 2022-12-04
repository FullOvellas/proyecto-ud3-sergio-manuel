package com.vaultapp.controller;

import com.vaultapp.model.repository.BookRepository;
import com.vaultapp.view.cli.BookCliView;
import jakarta.persistence.EntityManager;

public class BookController {
    private BookRepository bookRepository;
    private BookCliView bookCliView;

    public BookController(EntityManager entityManager) {
        bookRepository = BookRepository.getInstance();
        bookCliView = new BookCliView();
    }
}
