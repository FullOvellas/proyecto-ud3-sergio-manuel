package com.vaultapp.controller;

import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.entities.Vault;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookVaultDbRepository;
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
        User u =new User("root", "root");
        BookVault v = new BookVault("Literatura");
        u.addVault(v);
        UserRepository.getInstance().add(u);
        UserSession.getInstance().login(u);

        // BEGIN CLI PROGRAM
        new CommandController();
        //END PROGRAM
        JpaUtil.close();
    }




}
