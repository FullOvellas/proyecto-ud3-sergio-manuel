package com.vaultapp.controller;

import atlantafx.base.controls.Spacer;
import atlantafx.base.theme.Styles;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.VaultItem;
import com.vaultapp.model.repository.FilmRepository;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

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
            KeyValue kv = new KeyValue(rect.widthProperty(), 250, slide);
            KeyValue kv2 = new KeyValue(rect.opacityProperty(), 1, slide);
            KeyValue filmBtnGrow = new KeyValue(btnFilmView.minWidthProperty(), 170, slide);
            KeyValue bookBtnGrow = new KeyValue(btnBookView.minWidthProperty(), 170, slide);
            KeyFrame kf = new KeyFrame(Duration.millis(150d), kv, kv2, filmBtnGrow, bookBtnGrow);
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
            KeyFrame kf = new KeyFrame(Duration.millis(150.0), kv, kv2, filmBtnShrink, bookBtnShrink);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                rect.setVisible(false);
            });
            timeline.play();
            expanded = false;
        }

    }

    public void initialize() {
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

        if (filmsSelected) {
            ObservableList<Film> films = FXCollections.observableArrayList(FilmRepository.getInstance().getAsList());

        }

    }

}
