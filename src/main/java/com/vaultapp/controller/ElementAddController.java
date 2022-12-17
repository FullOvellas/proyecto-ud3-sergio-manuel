package com.vaultapp.controller;

import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.*;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookDbRepository;
import com.vaultapp.model.repository.FilmApiRepository;
import com.vaultapp.model.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class ElementAddController {

    private final String[] PROMPTS_FILM = {"Search %s by title"};
    private final String PROMPT_TERM = MainGUIController.isFilmsSelected() ? "film" : "book";
    private UserSession session = UserSession.getInstance();
    @FXML
    public HBox hbxSearch;
    @FXML
    public TextField txfSearch;
    @FXML
    public Button btnSearch;
    @FXML
    public TableView<VaultItem> tblSearchResults;
    @FXML
    public Button btnAddElement;

    public void initialize() {

        txfSearch.setPromptText(String.format(PROMPTS_FILM[0], PROMPT_TERM));
        tblSearchResults.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblSearchResults.requestFocus();


        if (MainGUIController.isFilmsSelected()) {

            TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<VaultItem, LocalDate> releaseCol = new TableColumn<>("Release");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            tblSearchResults.getColumns().addAll(titleCol, releaseCol);

        } else {

            TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<VaultItem, String> authorCol = new TableColumn<>("Author");
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            TableColumn<VaultItem, String> isbnCol = new TableColumn<>("ISBN");
            isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            TableColumn<VaultItem, String> releaseCol = new TableColumn<>("Release");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
            tblSearchResults.getColumns().addAll(titleCol, authorCol,isbnCol, releaseCol);

        }

    }

    public void onSearchClick(ActionEvent actionEvent) {

        String search = txfSearch.getText();

        if (!search.isEmpty()) {

            txfSearch.clear();
            tblSearchResults.getItems().clear();

            if (MainGUIController.isFilmsSelected()) {

                List<Film> results = FilmApiRepository.getInstance().getAsListByTitle(search);
                tblSearchResults.setItems(FXCollections.observableArrayList(results));

            } else {

                List<Book> results = BookApiRepository.getInstance().getAsListByTitle(search);
                tblSearchResults.setItems(FXCollections.observableArrayList(results));

            }

        }

    }

    public void onAddClick(ActionEvent actionEvent) {

        VaultItem item = tblSearchResults.getSelectionModel().getSelectedItem();

        if (item != null) {

            Vault vault = session.getLoggedUser().findVaultByName(MainGUIController.getSelectedVault().getName()).get(0);
            vault.addElement(item);
            MainGUIController.setSelectedVault(vault);
            UserRepository.getInstance().add(session.getLoggedUser());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Element added");
            alert.setHeaderText("Success");
            alert.setContentText("The selected item was added to your vault");

            alert.showAndWait();

        }

    }
}
