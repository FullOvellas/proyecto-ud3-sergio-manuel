package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookvaults")
public class BookVault extends Vault {

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
}
