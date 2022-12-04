package com.vaultapp.controller;

import com.vaultapp.model.repository.FilmRepository;
import com.vaultapp.view.cli.FilmCliView;
import jakarta.persistence.EntityManager;

public class FilmController {
    private FilmRepository filmRepository;
    private FilmCliView filmCliView;

    public FilmController(EntityManager entityManager) {
        filmRepository = new FilmRepository(entityManager);
        filmCliView = new FilmCliView();
    }
}
