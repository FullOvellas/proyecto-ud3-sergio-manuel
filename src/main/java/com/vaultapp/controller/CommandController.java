package com.vaultapp.controller;


import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.User;
import com.vaultapp.view.cli.CommandControllerView;

import java.util.*;

public class CommandController {
    private CommandControllerView view;
    private final Scanner sc;
    private boolean exit;

    private final Set<String> commands = Set.of(
            "login",
            "logout",
            "status",
            "create",
            "open",
            "delete",
            "search",
            "add",
            "exit",
            "--user",
            "--password",
            "--book",
            "--film",
            "--name",
            "--title",
            "--isbn",
            "--type",
            "--vault"
    );

    public CommandController() {
        this.view = new CommandControllerView();
        this.sc = new Scanner(System.in);
        exit = false;
    }

    public void commandLoop() {
        do {
            view.printPrompt();
            String input = sc.nextLine();
            List<String> parseInput = parser(input);
            if (parseInput != null) {
                processParserCommand(parseInput);
            }

        } while (!exit);

    }

    /**
     * Parse input command to get the list structure of
     * [action,args...] where action defines the method to use.
     *
     * @param lineCommand
     * @return List structured with all input information or null if commands not found.
     */
    public List<String> parser(String lineCommand) {
        String action;
        List<String> args = new LinkedList<>();
        List<String> noArgs = new ArrayList<>();
        String[] splitLineCommand = lineCommand.split(" ");

        noArgs.add(splitLineCommand[0]);
        Arrays.stream(splitLineCommand).filter(s -> s.startsWith("--")).forEach(noArgs::add);
        if (!commands.containsAll(noArgs)) {
            this.view.commandWarning();
            return null;
        }
        Arrays.stream(splitLineCommand).filter(s -> !noArgs.contains(s)).forEach(args::add);
        action = String.join("", noArgs);
        args.add(0, action);
        return args;
    }

    public void processParserCommand(List<String> parserCommand) {
        switch (parserCommand.get(0)) {
            case "exit":
                actionExit();
                break;
            case "login--user--password":
                actionLogin(parserCommand.get(1), parserCommand.get(2));
                break;
            case "logout":
                actionLogout();
                break;
            case "status--user":
                actionStatusUser();
                break;
            case "show--book":
                actionShowBook();
                break;
            case "show--film":
                actionShowFilm();
                break;
            case "create--name--type":
                actionCreateNameType();
                break;
            case "open--name":
                actionOpenName();
                break;
            case "delete--name":
                actionDeleteName();
                break;
            case "search--book--title":
                actionSearchBookTitle();
                break;
            case "add-isbn--vault":
                actionAddIsbnVault();
                break;
            case "delete--isbn--vault":
                actionDeleteIsbnVault();
        }
    }

    private void actionExit() {
        this.exit = true;
    }

    private void actionDeleteIsbnVault() {
    }

    private void actionAddIsbnVault() {
    }

    private void actionSearchBookTitle() {
    }

    private void actionDeleteName() {
    }

    private void actionOpenName() {
    }

    private void actionCreateNameType() {
    }

    private void actionShowFilm() {
    }

    private void actionShowBook() {
    }

    private void actionStatusUser() {
    }

    private void actionLogout() {
    }

    private void actionLogin(String name, String password) {
        if (!UserSession.getInstance().login(new User(name, password))) {
            view.loginError();
        } else {
            view.modifyPrompt(name);

        }
    }


}
