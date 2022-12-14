package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    @Override
    public void deleteElement(Book book) {
        books.remove(book);
    }


    public Book findByIsbn(String isbn) {
        List<Book> lb = books.stream().filter(b -> b.getIsbn().equals(isbn)).collect(Collectors.toList());
        if (lb.size() > 0) {
            return lb.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String str = name + "(" + books.size() + ")";
        return str;
    }
}
