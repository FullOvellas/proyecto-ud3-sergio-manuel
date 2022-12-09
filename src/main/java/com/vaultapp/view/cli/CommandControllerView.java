package com.vaultapp.view.cli;

import com.vaultapp.model.entities.Book;

import java.util.List;

public class CommandControllerView {
    private String[] prompt = {"", "> "};
    private final String COMMAND_NOT_FOUND = "Command not found";
    private final String LOGIN_ERROR = "Incorrect user or password. Please, try again.";
    private final String NO_SESION = "You must login before.";
    private final String VAUL_ALREADY_EXISTS = "This vault already exists. Try another name.";
    private final String SUCCESSFULLY_ACTION = "Action done successfully";
    private String status =
            "\n============================\n" +
                    "Username: %s\n" +
                    "Password: ********\n" +
                    "\n============================\n";


    public void modifyPrompt(String arg) {
        prompt[0] = "session-" + arg;
    }

    public void printPrompt() {
        System.out.printf(String.join("", prompt));
    }

    public void resetPrompt() {
        prompt[0] = "";
    }

    public void commandNotFoundView() {
        System.out.println(COMMAND_NOT_FOUND);
    }

    public void loginErrorView() {
        System.out.println(LOGIN_ERROR);
    }

    public void statusView(String name) {
        System.out.println(String.format(status, name));
    }

    public void vaultAlreadyExistsView() {
        System.out.println(VAUL_ALREADY_EXISTS);
    }

    public void successfullyActionView() {
        System.out.println(SUCCESSFULLY_ACTION);
    }

    public void noSesionView() {
        System.out.println(NO_SESION);
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


}
