package com.vaultapp.view.cli;

import com.vaultapp.model.entities.Film;

import java.util.List;

public class FilmCliView {
    public void viewUser(Film film) {
        System.out.println("Film: " + film);
    }

    public void viewAllUsers(List<Film> films) {
        films.forEach(film -> System.out.println("Film: " + film));
    }
}
