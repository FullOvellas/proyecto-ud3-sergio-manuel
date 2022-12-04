package com.vaultapp.controller;

import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

/**
 * Defines the logic of the program.
 *
 * @author Manuel Landín Gómez
 * @author Sergio Alonso Pazo
 */
public class MainController {
    private EntityManager em;
    private UserController userController;
    private FilmController filmController;
    private BookController bookController;


    public MainController() {
        this.em = JpaUtil.getEntityManager();
        this.userController = new UserController(em);
        this.filmController = new FilmController(em);
        this.bookController = new BookController(em);
        // LÓGICA DE NEGOCIO


        // FIN DE PROGRAMA
        em.close();
    }
}
