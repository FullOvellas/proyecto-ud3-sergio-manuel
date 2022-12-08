package com.vaultapp.controller;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.view.cli.BookCliView;

import java.util.List;
import java.util.Scanner;

public class BookController {
    private static final Scanner sc = new Scanner(System.in);
    private BookCliView bookCliView;

    public BookController() {
        bookCliView = new BookCliView();
    }

    public List<Book> bookFinder() {
        bookCliView.viewBookFinder();
        return BookApiRepository.getInstance().getAsList(sc.nextLine());
    }

    public BookCliView getBookCliView() {
        return bookCliView;
    }
}
