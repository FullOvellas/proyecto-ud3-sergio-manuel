package com.vaultapp.controller;

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


    public MainController() {
        // PERSISTENT DATA
        UserRepository.getInstance().add(new User("manuel", "1234"));

        // BEGIN CLI PROGRAM
        new CommandController();
        //END PROGRAM
        JpaUtil.close();
    }




}
