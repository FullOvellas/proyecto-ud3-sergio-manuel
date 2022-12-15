package com.vaultapp.controller;


import com.vaultapp.login.UserSession;
import com.vaultapp.model.entities.*;
import com.vaultapp.model.repository.BookApiRepository;
import com.vaultapp.model.repository.BookDbRepository;
import com.vaultapp.model.repository.FilmApiRepository;
import com.vaultapp.model.repository.UserRepository;
import com.vaultapp.view.cli.CommandControllerView;

import java.util.*;
import java.util.stream.Collectors;

public class CommandController {
    private CommandControllerView view;
    private final Scanner sc;
    private boolean exit;

    private final Set<String> reservedWords = Set.of(
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
            "-u",
            "--password",
            "-p",
            "--book",
            "-b",
            "--film",
            "-f",
            "--bookvault",
            "-bv",
            "--filmvault",
            "-fv",
            "--name",
            "--title",
            "--isbn",
            "--vault",
            "-v",
            "help"
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
        Arrays.stream(splitLineCommand).filter(s -> s.startsWith("-")).forEach(noArgs::add);
        if (!reservedWords.containsAll(noArgs)) {
            this.view.commandNotFoundView();
            return null;
        }
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < splitLineCommand.length; i++) {
            if (splitLineCommand[i].startsWith("-")) {
                index.add(i);
            }
        }
        StringBuilder composeArgs = new StringBuilder();
        for (int i : index) {
            if (splitLineCommand.length <= i + 1) {
                break;
            } else {
                for (int j = i + 1; j < splitLineCommand.length; j++) {
                    if (splitLineCommand[j].startsWith("-")) {
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
            case "login-u-p":
                actionLogin(parserCommand.get(1), parserCommand.get(2));
                return;
            case "help":
                actionHelp();
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
                case "create--bookvault":
                case "create-bv":
                    actionCreateBookVault(parserCommand.get(1));
                    break;
                case "create--filmvault":
                case "create-fv":
                    actionCreateFilmVault(parserCommand.get(1));
                    break;
                case "open--bookvault":
                case "open-bv":
                    actionOpenBookVault(parserCommand.get(1));
                    break;
                case "open--filmvault":
                case "open-fv":
                    actionOpenFilmVault(parserCommand.get(1));
                    break;
                case "delete--bookvault":
                case "delete-bv":
                    actionDeleteBookVault(parserCommand.get(1));
                    break;
                case "delete--filmvault":
                case "delete-fv":
                    actionDeleteFilmVault(parserCommand.get(1));
                    break;
                case "search--book":
                case "search-b":
                    actionSearchBookTitle(parserCommand.get(1));
                    break;
                case "search--film":
                case "search-f":
                    actionSearchFilmTitle(parserCommand.get(1));
                    break;
                case "add--book--isbn--vault":
                case "add-b--isbn-v":
                    actionAddBook(parserCommand.get(1), parserCommand.get(2));
                    break;
                case "add--film--tmid--vault":
                case "add-f-tmid-v":
                    actionAddFilm(parserCommand.get(1), parserCommand.get(2));
                    break;
                case "delete--book--isbn--vault":
                case "delete-b--isbn-v":
                    actionDeleteBook(parserCommand.get(1), parserCommand.get(2));
                    break;
                default:
                    view.commandNotFoundView();
            }
        }
    }

    private void actionHelp() {
        view.helpView();
    }

    private void actionCreateBookVault(String name) {
        User u = UserSession.getInstance().getLoggedUser();
        BookVault v = new BookVault(name);
        if (!u.getBookVaults().contains(v)) {
            u.addVault(v);
            view.successfullyActionView();
        } else {
            view.vaultAlreadyExistsView();
        }
        //update user status
        UserRepository.getInstance().add(u);
    }

    private void actionCreateFilmVault(String name) {
        User u = UserSession.getInstance().getLoggedUser();
        FilmVault v = new FilmVault(name);
        if (!u.getFilmVaults().contains(v)) {
            u.addVault(v);
            view.successfullyActionView();
        } else {
            view.vaultAlreadyExistsView();
        }
        //update user status
        UserRepository.getInstance().add(u);
    }

    private void actionDeleteBook(String isbn, String vaultName) {
        User u = UserSession.getInstance().getLoggedUser();
        List<BookVault> bvs = u.getBookVaults();
        for (BookVault bv : bvs) {
            if (bv.getName().equals(vaultName)) {
                Book b = bv.findByIsbn(isbn);
                if (b != null) {
                    bv.deleteElement(b);
                    view.removeBookView();
                    UserRepository.getInstance().add(u);
                    return;
                }
                view.bookNotFoundView();
                return;
            }
            view.vaultNotFoundView();
            return;
        }
    }

    private void actionAddFilm(String tmid, String vaultName) {
        //todo
    }

    private void actionAddBook(String isbn, String vaultName) {
        var vaultList = UserSession.getInstance().getLoggedUser().getBookVaults().stream().filter(bv -> bv.getName().equals(vaultName)).collect(Collectors.toList());
        // test if vaultList exists
        if (vaultList.size() == 0) {
            view.vaultNotFoundView();
            return;
        }
        if (vaultList.get(0).findByIsbn(isbn) != null) {
            view.bookAlreadyExistsView();
            return;
        }
        // test if isbn exists in db
        Book bdb = BookDbRepository.getInstance().findByIsbn(isbn);
        if (bdb == null) {
            // test if isbn exists in the api
            Book b = BookApiRepository.getInstance().getByIsbn(isbn);
            if (b == null) {
                view.bookNotFoundView();
                return;
            }
            vaultList.get(0).addElement(b);
        } else {
            vaultList.get(0).addElement(bdb);
        }
        view.successfullyActionView();
        //update user status
        UserRepository.getInstance().add(UserSession.getInstance().getLoggedUser());
    }

    private void actionSearchBookTitle(String title) {
        view.listOfBooksView(BookApiRepository.getInstance().getAsListByTitle(title));
    }

    private void actionSearchFilmTitle(String title) {
        view.listOfFilmsView(FilmApiRepository.getInstance().getAsListByTitle(title));
    }

    private void actionDeleteBookVault(String title) {
        User u = UserSession.getInstance().getLoggedUser();
        List<BookVault> bv = u.getBookVaults().stream().filter(v -> v.getName().equals(title)).collect(Collectors.toList());

        if (bv.isEmpty()) {
            view.vaultNotFoundView();
        } else {
            u.removeVault(bv.get(0));
            view.successfullyActionView();
        }
        UserRepository.getInstance().add(u);
    }

    private void actionDeleteFilmVault(String title) {
        User u = UserSession.getInstance().getLoggedUser();
        List<FilmVault> fv = u.getFilmVaults().stream().filter(v -> v.getName().equals(title)).collect(Collectors.toList());

        if (fv.isEmpty()) {
            view.vaultNotFoundView();
        } else {
            u.removeVault(fv.get(0));
            view.successfullyActionView();
        }
        UserRepository.getInstance().add(u);
    }

    private void actionOpenBookVault(String vaultName) {
        List<BookVault> bv = UserSession.getInstance().getLoggedUser().getBookVaults().stream().filter(v -> v.getName().equals(vaultName)).collect(Collectors.toList());
        if (bv.size() < 1) {
            view.vaultNotFoundView();
            return;
        }
        view.listOfBooksView(bv.get(0).getBooks());
    }

    private void actionOpenFilmVault(String vaultName) {
        List<FilmVault> fv = UserSession.getInstance().getLoggedUser().getFilmVaults().stream().filter(v -> v.getName().equals(vaultName)).collect(Collectors.toList());
        if (fv.size() < 1) {
            view.vaultNotFoundView();
            return;
        }
        view.listOfFilmsView(fv.get(0).getFilms());
    }


    private void actionStatusUser() {
        User u = UserSession.getInstance().getLoggedUser();
        view.statusView(u.getName(), u.getBookVaults(), u.getFilmVaults());
    }

    private void actionLogout() {
        if (UserSession.getInstance().logout()) {
            view.resetPrompt();
            view.logoutView();
        }
    }

    private void actionLogin(String name, String password) {
        if (!UserSession.getInstance().login(new User(name, password))) {
            view.loginErrorView();
        } else {
            view.modifyPrompt(name);
            view.welcomeView(name);
        }
    }

    private void actionExit() {
        this.exit = true;
    }
}
