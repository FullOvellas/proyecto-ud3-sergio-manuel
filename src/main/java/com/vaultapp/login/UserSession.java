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
        boolean ctrl = false;
        if (UserRepository.getInstance().find(user.getName()) != null) {
            loginUser = user;
            ctrl = true;
        }
        return ctrl;
    }

    public void logout() {
        loginUser = null;
    }

    public User getLoggedUser(){
        return loginUser;
    }

}
