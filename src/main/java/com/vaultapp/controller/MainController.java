package com.vaultapp.controller;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.entities.Vault;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

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


        User u = new User();
        Book b = new Book();
        BookVault v = new BookVault();
        v.addElement(b);
        u.addVault(v);
        userController.getUserRepo().add(u);
        userController.getUserRepo().getAsList().forEach(System.out::println);

        // FIN DE PROGRAMA
        JpaUtil.close();
    }
}
