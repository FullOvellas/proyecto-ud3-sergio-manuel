package com.vaultapp.view.cli;

import com.vaultapp.model.entities.User;

import java.util.List;

public class UserCliView {
    public void viewUser(User user) {
        System.out.println("User: " + user);
    }

    public void viewAllUsers(List<User> users) {
        users.forEach(user -> System.out.println("User: " + user));
    }
}
