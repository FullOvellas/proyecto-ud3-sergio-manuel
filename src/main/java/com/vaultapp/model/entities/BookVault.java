package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookvaults")
public class BookVault extends Vault<Book> {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_vaults",
            joinColumns = @JoinColumn(name = "bookvault_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"bookvault_id", "book_id"})
    )
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;


    public BookVault() {
        super();
        books = new ArrayList<>();
    }

    public BookVault(String name) {
        super(name);
        books = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public void addElement(Book book) {
        books.add(book);
    }
}
