package com.vaultapp.view.cli;

import com.vaultapp.model.entities.Book;

import java.util.List;

public class BookCliView {
    public void viewUser(Book book) {
        System.out.println("Book: " + book);
    }

    public void viewAllUsers(List<Book> books) {
        books.forEach(book -> System.out.println("Book: " + book));
    }
}
