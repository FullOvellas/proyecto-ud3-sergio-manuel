package com.vaultapp.controller;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookDbRepository;
import com.vaultapp.view.cli.BookCliView;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private static final Scanner sc = new Scanner(System.in);
    private BookDbRepository bookRepository;
    private BookCliView bookCliView;

    public BookController() {
        bookRepository = BookDbRepository.getInstance();
        bookCliView = new BookCliView();
    }

    public List<Book> bookFinder() {
        bookCliView.viewBookFinder();
        return BookApiRepository.getInstance().getAsList(sc.nextLine());
    }

    public BookDbRepository getBookRepository() {
        return bookRepository;
    }

    public BookCliView getBookCliView() {
        return bookCliView;
    }
}
