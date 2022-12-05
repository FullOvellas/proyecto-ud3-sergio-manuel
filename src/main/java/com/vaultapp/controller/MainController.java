package com.vaultapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.entities.Vault;
import com.vaultapp.model.pojos.books.Response;
import com.vaultapp.model.pojos.books.dao.BookApiDao;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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

        BookApiDao bad = new BookApiDao();
        List<Book> b = bad.read("eragon");
        b.forEach(System.out::println);


        // FIN DE PROGRAMA
        JpaUtil.close();
    }
}
