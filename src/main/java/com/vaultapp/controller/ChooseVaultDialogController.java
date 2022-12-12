package com.vaultapp.controller;

import com.vaultapp.MainGUI;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.FilmVault;
import com.vaultapp.model.entities.Vault;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import static atlantafx.base.theme.Styles.*;

public class ChooseVaultDialogController {

    private static ObservableList<Vault> vaultList;
    @FXML
    public TableView<Vault> vaultsTable;
    @FXML
    public Button btnSelect;
    @FXML
    public Button btnCancel;
    @FXML
    public Label prompt;

    public static void setVaultList(List<Vault> vaultList) {

        ChooseVaultDialogController.vaultList = FXCollections.observableList(vaultList);

    }

    public void initialize() {

        prompt.getStyleClass().add(TITLE_2);
        vaultsTable.getStyleClass().add(STRIPED);
        vaultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vaultsTable.setItems(vaultList);
        TableColumn<Vault, String> nameCol = new TableColumn<>("Vault name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        vaultsTable.getColumns().add(nameCol);

    }

    public void onBtnClick(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(btnSelect)) {

            MainGUIController.setSelectedVault(vaultsTable.getSelectionModel().getSelectedItem());

        }

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }
}
