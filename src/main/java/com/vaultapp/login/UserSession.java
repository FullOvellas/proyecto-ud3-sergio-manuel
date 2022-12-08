package com.vaultapp.login;

import com.vaultapp.model.entities.User;
import com.vaultapp.model.repository.UserRepository;

public class UserSession {
    private static UserSession instance;
    private User loginUser;

    static {
        instance = new UserSession();
    }

    public static UserSession getInstance() {
        return instance;
    }

    public boolean login(User user) {
        User u = UserRepository.getInstance().find(user.getName());
        if (user.equals(u)) {
            loginUser = user;

            return true;
        }
        return false;
    }

    public boolean logout() {
        if (loginUser != null) {
            loginUser = null;
            return true;
        }
        return false;
    }

    public User getLoggedUser(){
        return loginUser;
    }

}
