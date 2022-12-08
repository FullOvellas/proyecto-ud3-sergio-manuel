package com.vaultapp.view.cli;

public class CommandControllerView {
    private String[] prompt ={"", "> "} ;
    private final String COMMAND_NOT_FOUND = "Command not found";
    private final String LOGIN_ERROR = "Incorrect user or password. Please, try again.";


    public void commandWarning() {
        System.out.println(COMMAND_NOT_FOUND);
    }

    public void loginError() {
        System.out.println(LOGIN_ERROR);
    }

    public void modifyPrompt(String arg) {
        prompt[0] = "session-" + arg;
    }

    public void printPrompt() {
        System.out.printf(String.join("", prompt));
    }
}
