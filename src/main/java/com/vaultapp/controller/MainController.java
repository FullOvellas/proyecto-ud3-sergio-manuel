package com.vaultapp.controller;

import com.vaultapp.model.entities.User;
import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * Defines the logic of the program.
 *
 * @author Manuel Landín Gómez
 * @author Sergio Alonso Pazo
 */
public class MainController {


    public MainController() {
        // PERSISTENT DATA
        User u = new User("root", "root");
        UserRepository.getInstance().add(u);

        // BEGIN CLI PROGRAM
        new CommandController();
        //END PROGRAM
        JpaUtil.close();
    }

}
