package com.vaultapp.controller;

import com.vaultapp.model.repository.FilmRepository;
import com.vaultapp.view.cli.FilmCliView;

public class FilmController {
    private FilmRepository filmRepository;
    private FilmCliView filmCliView;

    public FilmController() {
        filmRepository = FilmRepository.getInstance();
        filmCliView = new FilmCliView();
    }

    public FilmRepository getFilmRepository() {
        return filmRepository;
    }

    public FilmCliView getFilmCliView() {
        return filmCliView;
    }
}
