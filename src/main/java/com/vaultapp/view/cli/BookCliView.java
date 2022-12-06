package com.vaultapp.view.cli;

import com.vaultapp.model.entities.Book;

import java.util.List;

public class BookCliView {

    public void viewBookFinder() {
        String view = "Buscar libro:\n >";
        System.out.print(view);
    }

    public void viewListOfBooks(List<Book> books) {
        String view = "=============\n" +
                "Title: %s\n" +
                "Author: %s\n" +
                "ISBN: %s\n" +
                "Publish Year: %s\n" +
                "Cover: %s\n";
        Book b;
        for (int i = 0; i < 10; i++) {
            b = books.get(i);
            System.out.println(String.format(view, b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishYear(), b.getCover()));
        }
    }
}
