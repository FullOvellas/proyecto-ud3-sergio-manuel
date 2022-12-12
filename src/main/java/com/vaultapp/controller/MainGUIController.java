package com.vaultapp.controller;

import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.VaultItem;
import com.vaultapp.model.repository.BookDbRepository;
import com.vaultapp.model.repository.FilmRepository;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalDate;

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
    private Rectangle rect;
    private final Interpolator slide = Interpolator.SPLINE(0, 0, 0.1, 1);
    private final String SIDEBAR_BORDER = "-fx-border-style: hidden solid hidden hidden";
    private final String HIDDEN_BORDER = "-fx-border-style: hidden";
    private double btnDefWidth;
    private double btnExpandedWidth = 180d;
    private Insets defPadding = new Insets(10d);
    private Insets expandedPadding;
    private boolean expanded = false;
    private boolean filmsSelected = true; // true: view film vault, false: view book vault

    public void expandRectangle(ActionEvent actionEvent) {
        if (!expanded) {
            btnFilmView.textAlignmentProperty().setValue(TextAlignment.LEFT);
            btnBookView.textAlignmentProperty().setValue(TextAlignment.LEFT);
            vaultControlsContainer.setAlignment(Pos.CENTER_LEFT);
            vbxSidebar.requestFocus();
            vbxSidebar.setStyle("-fx-fill: #3c536e");
            FontIcon icon = (FontIcon) btnBookView.getGraphic();
            icon.iconColorProperty().set(Paint.valueOf("#FFF"));
            vbxSidebar.setStyle(HIDDEN_BORDER);
            btnSideMenu.getStyleClass().add(ACCENT);
            btnFilmView.getStyleClass().add(ACCENT);
            btnBookView.getStyleClass().add(ACCENT);
            rect.setVisible(true);

            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            KeyValue kv = new KeyValue(rect.widthProperty(), 184, slide);
            KeyValue kv2 = new KeyValue(rect.opacityProperty(), 1, slide);
            KeyValue filmBtnGrow = new KeyValue(btnFilmView.minWidthProperty(), 170, slide);
            KeyValue bookBtnGrow = new KeyValue(btnBookView.minWidthProperty(), 170, slide);
            KeyValue layerOpacity = new KeyValue(layer.opacityProperty(), 0.7);
            KeyFrame kf = new KeyFrame(Duration.millis(150d), kv, kv2, filmBtnGrow, bookBtnGrow, layerOpacity);
            timeline.setOnFinished(e -> {
                btnFilmView.setText("view film vault");
                btnBookView.setText("view book vault");
                btnFilmView.maxWidthProperty().setValue(btnExpandedWidth);
                btnFilmView.setPadding(expandedPadding);
            });
            timeline.getKeyFrames().add(kf);
            timeline.play();
            expanded = true;

        } else {
            btnFilmView.textAlignmentProperty().setValue(TextAlignment.CENTER);
            btnBookView.textAlignmentProperty().setValue(TextAlignment.CENTER);
            btnFilmView.setPadding(defPadding);
            vaultControlsContainer.setAlignment(Pos.CENTER);
            btnFilmView.setText("");
            btnBookView.setText("");
            vbxSidebar.setStyle("-fx-fill: rgba(0,0,0,0)");
            btnSideMenu.getStyleClass().remove(ACCENT);
            btnBookView.getStyleClass().remove(ACCENT);
            btnFilmView.getStyleClass().remove(ACCENT);
            vbxSidebar.setStyle(SIDEBAR_BORDER);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            KeyValue kv = new KeyValue(rect.widthProperty(), 0, slide);
            KeyValue kv2 = new KeyValue(rect.opacityProperty(), 0, slide);
            KeyValue filmBtnShrink = new KeyValue(btnFilmView.minWidthProperty(), btnDefWidth);
            KeyValue bookBtnShrink = new KeyValue(btnBookView.minWidthProperty(), btnDefWidth);
            KeyValue layerOpacity = new KeyValue(layer.opacityProperty(), 0d);
            KeyFrame kf = new KeyFrame(Duration.millis(150.0), kv, kv2, filmBtnShrink, bookBtnShrink, layerOpacity);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                rect.setVisible(false);
            });
            timeline.play();
            expanded = false;
        }

    }

    public void initialize() {

        // Control flow
        EventHandler<MouseEvent> enableControls = e -> controlsLayer.setMouseTransparent(false);
        EventHandler<MouseEvent> disableControls = e -> controlsLayer.setMouseTransparent(true);
        detailView.setOnMouseEntered(disableControls);
        vbxSidebar.setOnMouseExited(disableControls);
        tblItems.setOnMouseExited(enableControls);

        // Element initialization
        title.setText(filmsSelected ? "Your film vault" : "Your book vault");
        title.getStyleClass().add(TITLE_1);
        vbxSidebar.setPadding(new Insets(0, 3, 0, 2));
        btnSideMenu.setGraphic(new FontIcon("bi-list"));
        btnSideMenu.getStyleClass().add(LARGE);
        FontIcon icon = new FontIcon("bi-book");
        btnBookView.setGraphic(icon);
        btnBookView.getStyleClass().addAll(FONT_ICON, LARGE);
        btnFilmView.setGraphic(new FontIcon("bi-film"));
        btnFilmView.getStyleClass().addAll(FONT_ICON, LARGE);
        btnDefWidth = btnFilmView.getWidth();
        expandedPadding = new Insets(10d, 15d, 10d, 5d);
        spacer.minWidthProperty().setValue(58);
        titleSpacer.minWidthProperty().setValue(62);
        tblItems.getStyleClass().add(STRIPED);
        detailView.setPadding(new Insets(5d));
        detailView.setMaxWidth(300d);
        itemImage.setFitHeight(300);
        itemImage.setFitWidth(200);
        itemInfo.autosize();

        if (filmsSelected) {

            ObservableList<VaultItem> films = FXCollections.observableArrayList(FilmRepository.getInstance().getAsList());
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

    public void switchToFilmVault(ActionEvent actionEvent) {

        if (filmsSelected) {
            expandRectangle(actionEvent);
        } else {

            filmsSelected = true;
            title.setText("Your film vault");

            ObservableList<VaultItem> films = FXCollections.observableArrayList(FilmRepository.getInstance().getAsList());
            tblItems.setItems(films);
            TableColumn<Film, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<Film, LocalDate> releaseCol = new TableColumn<>("Year");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            TableColumn<Film, String> statusCol = new TableColumn<>("Status");
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            expandRectangle(actionEvent);

        }

    }

    public void switchToBookVault(ActionEvent actionEvent) {

        if (!filmsSelected) {

            expandRectangle(actionEvent);

        } else {

            layer.setMouseTransparent(true);
            filmsSelected = false;
            title.setText("Your book vault");

            ObservableList<VaultItem> books = FXCollections.observableArrayList(BookDbRepository.getInstance().getAsList());
            tblItems.setItems(books);
            TableColumn<Film, String> titleCol = new TableColumn<>("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<Film, String> authorCol = new TableColumn<>("Author");
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            TableColumn<Film, String> releaseCol = new TableColumn<>("Year");
            releaseCol.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
            TableColumn<Film, String> statusCol = new TableColumn<>("Status");
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
            expandRectangle(actionEvent);

        }
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

        }

    }
}
