package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filmvaults")
public class FilmVault extends Vault<Film> {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "films_vaults",
            joinColumns = @JoinColumn(name = "filmvault_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"filmvault_id", "film_id"})
    )
    private List<Film> films;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public FilmVault() {
        films = new ArrayList<>();
    }

    public List<Film> getBooks() {
        return films;
    }

    public void setBooks(List<Film> films) {
        this.films = films;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public void addElement(Film film) {
        films.add(film);
    }

    @Override
    public void deleteElement(Film film) {
        films.remove(film);
    }

    @Override
    public String toString() {
        return "FilmVault{" +
                "films=" + films +
                '}';
    }
}
