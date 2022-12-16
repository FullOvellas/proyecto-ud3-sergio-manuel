package com.vaultapp.controller;

import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.FilmVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameInputController {


    @FXML
    public TextField txfVaultName;
    @FXML
    public Button btnConfirmName;

    public void onNameConfirmClick(ActionEvent actionEvent) {

        User activeUser = MainGUIController.getActiveUser();
        String name = txfVaultName.getText();

        if (!name.isEmpty() && ChooseVaultDialogController.getVaultList().stream().noneMatch(vault -> vault.getName().equals(name))) {

            if (ChooseVaultDialogController.isChoosingFilms()) {

                FilmVault vault = new FilmVault(name);
                activeUser.addVault(vault);
                UserRepository.getInstance().add(activeUser);
                MainGUIController.setSelectedVault(vault);

            } else {

                BookVault vault = new BookVault(name);
                activeUser.addVault(vault);
                UserRepository.getInstance().add(activeUser);
                MainGUIController.setSelectedVault(vault);


            }

            Stage stage = (Stage) btnConfirmName.getScene().getWindow();
            stage.close();

        }

    }
}
