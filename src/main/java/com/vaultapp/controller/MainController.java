package com.vaultapp.controller;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.pojo.book.dao.BookApiDao;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.utilities.JpaUtil;

import java.util.List;
import java.util.Scanner;

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
        mainLoop();
        JpaUtil.close();
    }

    private void mainLoop() {
        while(true) {

        }
    }




}
