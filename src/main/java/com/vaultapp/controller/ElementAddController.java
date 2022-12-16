package com.vaultapp.controller;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.VaultItem;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookDbRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class ElementAddController {

    private final String[] PROMPTS_FILM = {"Search %s by title"};
    private final String[] SPINNER_FIELDS = {"By title"};
    private final String PROMPT_TERM = MainGUIController.isFilmsSelected() ? "film" : "book";
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
        tblSearchResults.requestFocus();


        if (MainGUIController.isFilmsSelected()) {

            TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<VaultItem, LocalDate> releaseCol = new TableColumn<>("Year");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            tblSearchResults.getColumns().addAll(titleCol, releaseCol);

        } else {

            TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<VaultItem, String> authorCol = new TableColumn<>("Author");
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            TableColumn<VaultItem, String> releaseCol = new TableColumn<>("Year");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
            tblSearchResults.getColumns().addAll(titleCol, authorCol, releaseCol);

        }

    }

    public void onSearchClick(ActionEvent actionEvent) {

        String search = txfSearch.getText();


        if (!search.isEmpty()) {

            if (MainGUIController.isFilmsSelected()) {

                // TODO: search in db and api

            } else {

                // TODO: search in db and api

            }

        }

    }
}
