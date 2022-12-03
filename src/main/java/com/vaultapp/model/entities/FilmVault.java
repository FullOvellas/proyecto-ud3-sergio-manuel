package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "filmvaults")
public class FilmVault extends Vault {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "films_vaults",
            joinColumns = @JoinColumn(name = "filmvault_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"filmvault_id", "film_id"})
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
