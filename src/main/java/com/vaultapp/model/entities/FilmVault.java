package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public FilmVault(String name) {
        this();
        this.name = name;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
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
        if (!films.contains(film)) {
            films.add(film);
        }
    }

    @Override
    public void deleteElement(Film film) {
        films.remove(film);
    }

    public Film findByTmid(String tmid) {
        List<Film> lf = films.stream().filter(f -> String.valueOf(f.getTmdbId()).equals(tmid)).collect(Collectors.toList());
        if (lf.size() > 0) {
            return lf.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str = name + "(" + films.size() + ")";
        return str;
    }
}
