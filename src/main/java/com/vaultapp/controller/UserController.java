package com.vaultapp.controller;

import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.view.cli.UserCliView;

public class UserController {
    private UserRepository userRepo;
    private UserCliView userCliView;

    public UserController() {
        userRepo = UserRepository.getInstance();
        userCliView = new UserCliView();
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public UserCliView getUserCliView() {
        return userCliView;
    }
}
