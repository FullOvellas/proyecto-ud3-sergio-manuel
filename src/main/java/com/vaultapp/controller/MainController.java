package com.vaultapp.controller;

import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.utilities.JpaUtil;


/**
 * Defines the logic of the program.
 *
 * @author Manuel Landín Gómez
 * @author Sergio Alonso Pazo
 */
public class MainController {
    private UserController userController;
    private FilmController filmController;
    private BookController bookController;


    public MainController() {
        this.userController = new UserController();
        this.filmController = new FilmController();
        this.bookController = new BookController();
        // LÓGICA DE NEGOCIO

        User u = new User("Manuel");
        UserRepository.getInstance().add(u);
        System.out.println(UserSession.getInstance().login(u));
        System.out.println(UserSession.getInstance().getLoggedUser());


        // FIN DE PROGRAMA
        JpaUtil.close();
    }
}
