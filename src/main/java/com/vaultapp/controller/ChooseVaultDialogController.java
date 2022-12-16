package com.vaultapp.controller;

import com.vaultapp.MainGUI;
import com.vaultapp.model.entities.Vault;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    public Button btnAdd;
    private static boolean choosingFilms;

    public static ObservableList<Vault> getVaultList() {
        return vaultList;
    }

    public static boolean isChoosingFilms() {
        return choosingFilms;
    }

    public static void setChoosingFilms(boolean choosingFilms) {
        ChooseVaultDialogController.choosingFilms = choosingFilms;
    }

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

    public void onBtnClick(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource().equals(btnSelect)) {

            MainGUIController.setSelectedVault(vaultsTable.getSelectionModel().getSelectedItem());

        } else if (actionEvent.getSource().equals(btnAdd)) {

            FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("vaultNameInput-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initOwner(MainGUI.getMainStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.showAndWait();

        }
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
