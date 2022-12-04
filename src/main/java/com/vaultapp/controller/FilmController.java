package com.vaultapp.controller;

import com.vaultapp.model.repository.FilmRepository;
import com.vaultapp.view.cli.FilmCliView;
import jakarta.persistence.EntityManager;

public class FilmController {
    private FilmRepository filmRepository;
    private FilmCliView filmCliView;

    public FilmController(EntityManager entityManager) {
        filmRepository = FilmRepository.getInstance();
        filmCliView = new FilmCliView();
    }
}
