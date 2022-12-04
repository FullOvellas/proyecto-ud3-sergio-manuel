package com.vaultapp.controller;

import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.view.cli.UserCliView;
import jakarta.persistence.EntityManager;

public class UserController {
    private UserRepository userRepo;
    private UserCliView userCliView;

    public UserController(EntityManager entityManager) {
        userRepo = new UserRepository(entityManager);
        userCliView = new UserCliView();
    }
}
