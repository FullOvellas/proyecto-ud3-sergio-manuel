package com.vaultapp.controller;


import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.entities.Vault;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookVaultDbRepository;
import com.vaultapp.model.repository.UserRepository;
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
        this.commandLoop();
    }

    private void commandLoop() {
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
    private List<String> parser(String lineCommand) {
        String action;
        List<String> args = new LinkedList<>();
        List<String> noArgs = new ArrayList<>();
        String[] splitLineCommand = lineCommand.split(" ");

        noArgs.add(splitLineCommand[0]);
        Arrays.stream(splitLineCommand).filter(s -> s.startsWith("--")).forEach(noArgs::add);
        if (!commands.containsAll(noArgs)) {
            this.view.commandNotFoundView();
            return null;
        }
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < splitLineCommand.length; i++) {
            if (splitLineCommand[i].contains("--")) {
                index.add(i);
            }
        }
        StringBuilder composeArgs = new StringBuilder();
        for (int i : index) {
            if (splitLineCommand.length <= i + 1) {
                break;
            } else {
                for (int j = i + 1; j < splitLineCommand.length; j++) {
                    if (splitLineCommand[j].contains("--")) {
                        break;
                    }
                    composeArgs.append(splitLineCommand[j]).append(" ");
                }
                if (!composeArgs.toString().isBlank() && !composeArgs.toString().isEmpty()) {
                    args.add(composeArgs.toString().strip());
                    composeArgs.setLength(0);
                }
            }
        }
        //Arrays.stream(splitLineCommand).filter(s -> !noArgs.contains(s)).forEach(args::add);
        action = String.join("", noArgs);
        args.add(0, action);
        return args;
    }

    private void processParserCommand(List<String> parserCommand) {
        System.out.println(parserCommand);
        switch (parserCommand.get(0)) {
            case "exit":
                actionExit();
                return;
            case "login--user--password":
                actionLogin(parserCommand.get(1), parserCommand.get(2));
                return;
        }

        // Session is needed active to work
        if (!UserSession.getInstance().inSession()) {
            view.noSesionView();
        } else {
            switch (parserCommand.get(0)) {
                case "logout":
                    actionLogout();
                    break;
                case "status":
                    actionStatusUser();
                    break;
                case "show--book":
                    actionShowBook();
                    break;
                case "show--film":
                    actionShowFilm();
                    break;
                case "create--name--type":
                    actionCreateNameType(parserCommand.get(1), parserCommand.get(2));
                    break;
                case "open--name":
                    actionOpenName();
                    break;
                case "delete--name":
                    actionDeleteName();
                    break;
                case "search--book--title":
                    actionSearchBookTitle(parserCommand.get(1));
                    break;
                case "add--isbn--vault":
                    actionAddIsbnVault();
                    break;
                case "delete--isbn--vault":
                    actionDeleteIsbnVault();
                    break;
                default:
                    view.commandNotFoundView();
            }
        }
    }

    private void actionExit() {
        this.exit = true;
    }

    private void actionDeleteIsbnVault() {
    }

    private void actionAddIsbnVault() {
    }

    private void actionSearchBookTitle(String title) {
        view.listOfBooksView(BookApiRepository.getInstance().getAsList(title));
    }

    private void actionDeleteName() {
    }

    private void actionOpenName() {
    }

    private void actionCreateNameType(String name, String type) {
        if (type.equals("book")) {
            BookVault v = new BookVault(name);
            if (!UserSession.getInstance().getLoggedUser().getBookVaults().contains(v)) {
                UserSession.getInstance().getLoggedUser().addVault(v);
                view.successfullyActionView();
            } else {
                view.vaultAlreadyExistsView();
            }
            UserRepository.getInstance().add(UserSession.getInstance().getLoggedUser());
        }
    }

    private void actionShowFilm() {
    }

    private void actionShowBook() {
    }

    private void actionStatusUser() {
        User u = UserSession.getInstance().getLoggedUser();
        view.statusView(u.getName());
    }

    private void actionLogout() {
        if (UserSession.getInstance().logout()) {
            view.resetPrompt();
        }
    }

    private void actionLogin(String name, String password) {
        if (!UserSession.getInstance().login(new User(name, password))) {
            view.loginErrorView();
        } else {
            view.modifyPrompt(name);
        }
    }


}
