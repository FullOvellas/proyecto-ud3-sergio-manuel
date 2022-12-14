package com.vaultapp.view.cli;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.FilmVault;

import java.util.List;

public class CommandControllerView {

    public static final String COMMAND_NOT_FOUND = "Command not found.\n";
    public static final String LOGIN_ERROR = "Incorrect user or password. Please, try again.\n";
    public static final String NO_SESION = "You must login before.\n";
    public static final String VAULT_ALREADY_EXISTS = "This vault already exists. Try another name.\n";
    public static final String VAULT_NOT_FOUND = "Vault not found. Try another vault name.\n";
    public static final String BOOK_NOT_FOUND = "Book not found. The ISBN may be wrong. Please, try again.\n";
    public static final String BOOK_ALREADY_EXISTS = "Book already exists in the vault.\n";
    public static final String SUCCESSFULLY_ACTION = "Action done successfully.\n";
    public static final String LOGOUT = "Log out.\n";
    public static final String REMOVE_BOOK = "\n" + "Book has been successfully deleted.";

    private final String[] PROMPT = {"", "> "};
    private final String WELCOME = "Welcome %s.\n";
    private final String STATUS =
            "\n============================\n" +
                    "Username: %s\n" +
                    "---------------------\n" +
                    "BookVaults: %s\n" +
                    "FilmVaults: %s\n" +
                    "---------------------\n" +
                    "============================\n";


    public void welcomeView(String arg) {
        System.out.println(String.format(WELCOME, arg));
    }

    public void bookAlreadyExistsView() {
        System.out.println(BOOK_ALREADY_EXISTS);
    }

    public void logoutView() {
        System.out.println(LOGOUT);
    }

    public void removeBookView() {
        System.out.println(REMOVE_BOOK);
    }

    public void modifyPrompt(String arg) {
        PROMPT[0] = "session-" + arg;
    }

    public void printPrompt() {
        System.out.printf(String.join("", PROMPT));
    }

    public void resetPrompt() {
        PROMPT[0] = "";
    }

    public void commandNotFoundView() {
        System.out.println(COMMAND_NOT_FOUND);
    }

    public void loginErrorView() {
        System.out.println(LOGIN_ERROR);
    }

    public void statusView(String name, List<BookVault> bookVaults, List<FilmVault> filmVaults) {
        System.out.println(String.format(STATUS, name, bookVaults, filmVaults));
    }

    public void vaultAlreadyExistsView() {
        System.out.println(VAULT_ALREADY_EXISTS);
    }

    public void successfullyActionView() {
        System.out.println(SUCCESSFULLY_ACTION);
    }

    public void noSesionView() {
        System.out.println(NO_SESION);
    }

    public void bookNotFoundView() {
        System.out.println(BOOK_NOT_FOUND);
    }

    public void vaultNotFoundView() {
        System.out.println(VAULT_NOT_FOUND);
    }



    public void listOfBooksView(List<Book> books) {
        String view = "=============\n" +
                "Title: %s\n" +
                "Author: %s\n" +
                "ISBN: %s\n" +
                "Publish Year: %s\n" +
                "Cover: %s\n";
        for (Book b : books) {
            System.out.println(String.format(view, b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishYear(), b.getCover()));
        }
    }

    public void listOfFilmsView(List<Film> films) {
        String view = "=============\n" +
                "Title: %s\n" +
                "Genres: %s\n" +
                "Id: %s\n" +
                "Publish Year: %s\n" +
                "Cover: %s\n";
        for (Film f : films) {
            System.out.println(String.format(view, f.getTitle(), f.getGenres(), f.getTmdbId(), f.getReleaseDate(), f.getPosterPath()));
        }
    }


}
