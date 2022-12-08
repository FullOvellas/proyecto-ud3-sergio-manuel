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
        // BEGIN PROGRAM

        var res = bookController.bookFinder();



        //END PROGRAM
        JpaUtil.close();
    }




}
