package com.vaultapp.controller;


import com.vaultapp.MainGUI;
import com.vaultapp.model.entities.*;
import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.VaultItem;
import com.vaultapp.model.repository.FilmDbRepository;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static atlantafx.base.theme.Styles.*;

public class MainGUIController {

    @FXML
    public Button btnSideMenu;
    @FXML
    public Button btnBookView;
    @FXML
    public Button btnFilmView;
    @FXML
    public VBox vbxSidebar;
    @FXML
    public VBox vaultControlsContainer;
    @FXML
    public TableView<VaultItem> tblItems;
    @FXML
    public Region spacer;
    @FXML
    public Label title;
    @FXML
    public Region titleSpacer;
    public Rectangle layer;
    @FXML
    public BorderPane controlsLayer;
    @FXML
    public BorderPane userContent;
    @FXML
    public ImageView itemImage;
    @FXML
    public Label detailField1;
    @FXML
    public Label detailField2;
    @FXML
    public Label detailField3;
    @FXML
    public Label detailField4;
    @FXML
    public VBox detailView;
    @FXML
    public VBox itemInfo;
    @FXML
    public Rectangle topLayer;
    @FXML
    public Button btnAddView;
    @FXML
    private Rectangle rect;
    private final Interpolator slide = Interpolator.SPLINE(0, 0, 0.1, 1);
    private final String SIDEBAR_BORDER = "-fx-border-style: hidden solid hidden hidden";
    private final String HIDDEN_BORDER = "-fx-border-style: hidden";
    private double btnDefWidth;
    private double btnExpandedWidth = 180d;
    private Insets defPadding = new Insets(10d);
    private Insets expandedPadding;
    private boolean expanded = false;
    private static boolean filmsSelected = true; // true: view film vault, false: view book vault
    private static Vault selectedVault;
    private static User activeUser;

    public static boolean isFilmsSelected() {
        return filmsSelected;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setSelectedVault(Vault selectedVault) {
        MainGUIController.selectedVault = selectedVault;
    }

    public static Vault getSelectedVault() {
        return selectedVault;
    }

    public void expandRectangle(ActionEvent actionEvent) {
        if (!expanded) {
            btnFilmView.textAlignmentProperty().setValue(TextAlignment.LEFT);
            btnBookView.textAlignmentProperty().setValue(TextAlignment.LEFT);
            btnAddView.textAlignmentProperty().setValue(TextAlignment.LEFT);
            vaultControlsContainer.setAlignment(Pos.CENTER_LEFT);
            vbxSidebar.requestFocus();
            vbxSidebar.setStyle("-fx-fill: #3c536e");
            FontIcon icon = (FontIcon) btnBookView.getGraphic();
            icon.iconColorProperty().set(Paint.valueOf("#FFF"));
            vbxSidebar.setStyle(HIDDEN_BORDER);
            btnSideMenu.getStyleClass().add(ACCENT);
            btnFilmView.getStyleClass().add(ACCENT);
            btnBookView.getStyleClass().add(ACCENT);
            btnAddView.getStyleClass().add(ACCENT);
            rect.setVisible(true);

            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            KeyValue kv = new KeyValue(rect.widthProperty(), 220, slide);
            KeyValue kv2 = new KeyValue(rect.opacityProperty(), 1, slide);
            KeyValue filmBtnGrow = new KeyValue(btnFilmView.minWidthProperty(), 207, slide);
            KeyValue bookBtnGrow = new KeyValue(btnBookView.minWidthProperty(), 170, slide);
            KeyValue addBtnGrow = new KeyValue(btnAddView.minWidthProperty(), 170, slide);
            KeyValue layerOpacity = new KeyValue(layer.opacityProperty(), 0.7);
            KeyFrame kf = new KeyFrame(Duration.millis(150d), kv, kv2, filmBtnGrow, bookBtnGrow, addBtnGrow, layerOpacity);
            timeline.setOnFinished(e -> {
                btnFilmView.setText("select film vault");
                btnBookView.setText("select book vault");
                btnAddView.setText("add item to vault");
                btnFilmView.maxWidthProperty().setValue(btnExpandedWidth);
                //btnFilmView.setPadding(expandedPadding);
            });
            timeline.getKeyFrames().add(kf);
            timeline.play();
            expanded = true;

        } else {
            btnFilmView.textAlignmentProperty().setValue(TextAlignment.CENTER);
            btnBookView.textAlignmentProperty().setValue(TextAlignment.CENTER);
            btnAddView.textAlignmentProperty().setValue(TextAlignment.CENTER);
            //btnFilmView.setPadding(defPadding);
            vaultControlsContainer.setAlignment(Pos.CENTER);
            btnFilmView.setText("");
            btnBookView.setText("");
            btnAddView.setText("");
            vbxSidebar.setStyle("-fx-fill: rgba(0,0,0,0)");
            btnSideMenu.getStyleClass().remove(ACCENT);
            btnBookView.getStyleClass().remove(ACCENT);
            btnFilmView.getStyleClass().remove(ACCENT);
            btnAddView.getStyleClass().remove(ACCENT);
            vbxSidebar.setStyle(SIDEBAR_BORDER);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            KeyValue kv = new KeyValue(rect.widthProperty(), 0, slide);
            KeyValue kv2 = new KeyValue(rect.opacityProperty(), 0, slide);
            KeyValue filmBtnShrink = new KeyValue(btnFilmView.minWidthProperty(), btnDefWidth);
            KeyValue bookBtnShrink = new KeyValue(btnBookView.minWidthProperty(), btnDefWidth);
            KeyValue addBtnShrink = new KeyValue(btnAddView.minWidthProperty(), btnDefWidth);
            KeyValue layerOpacity = new KeyValue(layer.opacityProperty(), 0d);
            KeyFrame kf = new KeyFrame(Duration.millis(150.0), kv, kv2, filmBtnShrink, bookBtnShrink, layerOpacity, addBtnShrink);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                rect.setVisible(false);
            });
            timeline.play();
            expanded = false;
        }

    }

    public void initialize() {

        //TODO: THIS IS FOR TESTING ////////////////////////
        //
        UserRepository.getInstance().add(new User("root", "root"));
        activeUser = UserRepository.getInstance().find("root");
        activeUser.addVault(new BookVault("testing"));
        UserRepository.getInstance().add(activeUser);
        selectedVault = new BookVault();
        //
        /////////////////////////////////////////////////////

        // Control flow
        EventHandler<MouseEvent> enableControls = e -> controlsLayer.setMouseTransparent(false);
        EventHandler<MouseEvent> disableControls = e -> controlsLayer.setMouseTransparent(true);
        detailView.setOnMouseEntered(disableControls);
        vbxSidebar.setOnMouseExited(disableControls);
        tblItems.setOnMouseExited(enableControls);
        title.setOnMouseEntered(disableControls);
        title.setOnMouseExited(enableControls);

        // Element initialization
        title.setText(filmsSelected ? "Your film vault" : "Your book vault");
        title.getStyleClass().add(TITLE_1);
        vbxSidebar.setPadding(new Insets(0, 3, 0, 2));
        btnSideMenu.setGraphic(new FontIcon("bi-list"));
        btnSideMenu.getStyleClass().add(LARGE);
        btnBookView.setGraphic(new FontIcon("bi-book"));
        btnBookView.getStyleClass().addAll(FONT_ICON, LARGE);
        btnFilmView.setGraphic(new FontIcon("bi-film"));
        btnFilmView.getStyleClass().addAll(FONT_ICON, LARGE);
        btnAddView.setGraphic(new FontIcon("bi-plus"));
        btnAddView.getStyleClass().addAll(FONT_ICON, LARGE);

        btnDefWidth = btnFilmView.getWidth();
        expandedPadding = new Insets(10d, 15d, 10d, 5d);
        spacer.minWidthProperty().setValue(58);
        titleSpacer.minWidthProperty().setValue(62);
        tblItems.getStyleClass().add(STRIPED);
        tblItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        detailView.setPadding(new Insets(5d));
        detailView.setMaxWidth(300d);
        itemImage.setFitHeight(300);
        itemImage.setFitWidth(200);
        itemInfo.autosize();

        if (filmsSelected) {

            ObservableList<VaultItem> films = FXCollections.observableArrayList(FilmDbRepository.getInstance().getAsList());
            tblItems.setItems(films);
            TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<VaultItem, LocalDate> releaseCol = new TableColumn<>("Release");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            //TableColumn<VaultItem, String> statusCol = new TableColumn<>("Status");
            //statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
            tblItems.getColumns().addAll(titleCol, releaseCol/*, statusCol*/);

        }

    }

    public void chooseVault(ActionEvent actionEvent) throws IOException {

        darkenAll();

        List<Vault> vaults;

        if (actionEvent.getSource().equals(btnBookView)) {

            vaults = new ArrayList<>(activeUser.getBookVaults());
            ChooseVaultDialogController.setChoosingFilms(false);

        } else {

            vaults = new ArrayList<>(activeUser.getFilmVaults());
            ChooseVaultDialogController.setChoosingFilms(true);

        }

        Vault oldVault = selectedVault;

        ChooseVaultDialogController.setVaultList(vaults);
        launchDialog("chooseVaultDialog-view.fxml");

        lightenAll();

        if (!oldVault.equals(selectedVault) || oldVault == null) {

            if (selectedVault instanceof BookVault) {

                swapToBookVault();

            } else {

                swapToFilmVault();

            }

        }

    }

    private void lightenAll() {

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyValue kv = new KeyValue(topLayer.opacityProperty(), 0.0, slide);
        KeyFrame kf = new KeyFrame(Duration.millis(150d), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    private void darkenAll() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyValue kv = new KeyValue(topLayer.opacityProperty(), 0.7, slide);
        KeyFrame kf = new KeyFrame(Duration.millis(150d), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void swapToFilmVault() {

        tblItems.getItems().clear();
        tblItems.getColumns().clear();
        FilmVault vault = (FilmVault) selectedVault;
        title.setText("Your film vault: " + selectedVault.getName());
        filmsSelected = true;

        ObservableList<VaultItem> films = FXCollections.observableArrayList(vault.getFilms());
        tblItems.setItems(films);
        TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<VaultItem, LocalDate> releaseCol = new TableColumn<>("Release");
        releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        //TableColumn<VaultItem, String> statusCol = new TableColumn<>("Status");
        //statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblItems.getColumns().addAll(titleCol, releaseCol/*, statusCol*/);

    }

    private void swapToBookVault() {

        tblItems.getItems().clear();
        tblItems.getColumns().clear();
        BookVault vault = (BookVault) selectedVault;
        title.setText("Your book vault: " + selectedVault.getName());
        filmsSelected = false;

        ObservableList<VaultItem> books = FXCollections.observableArrayList(vault.getBooks());
        tblItems.setItems(books);
        TableColumn<VaultItem, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<VaultItem, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<VaultItem, LocalDate> releaseCol = new TableColumn<>("Release");
        releaseCol.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
        //TableColumn<VaultItem, String> statusCol = new TableColumn<>("Status");
        //statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblItems.getColumns().addAll(titleCol, releaseCol/*, statusCol*/);

    }

    public void refreshDetailView(MouseEvent mouseEvent) {

        if (filmsSelected) {

            Film f = (Film) tblItems.getSelectionModel().getSelectedItem();

            itemImage.setImage(new Image(f.getPosterPath()));

            detailField1.setText("Title: " + f.getTitle());
            detailField2.setText("Genres: " + String.join(", ", f.getGenres()));
            detailField3.setText("Overview: " + f.getOverview());
            detailField4.setText("Tagline: " + f.getTagline());

        } else {

            Book b = (Book) tblItems.getSelectionModel().getSelectedItem();

            itemImage.setImage(new Image(b.getCover().toString()));

            detailField1.setText("Title: " + b.getTitle());
            detailField2.setText("Author: " + b.getAuthor());
            detailField3.setText("Publish date: " + b.getPublishYear());
            detailField4.setText("ISBN" + b.getIsbn());

        }

    }

    public void onAddItemClick(ActionEvent actionEvent) throws IOException {

        darkenAll();
        launchDialog("elementAdd-view.fxml");
        lightenAll();


    }

    private void launchDialog(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initOwner(MainGUI.getMainStage());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
