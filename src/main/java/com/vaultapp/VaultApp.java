package com.vaultapp;

import com.vaultapp.model.entities.Film;
import com.vaultapp.model.pojo.film.dao.FilmApiDao;
import com.vaultapp.model.repository.FilmDbRepository;

import java.util.List;
import java.util.Locale;

public class VaultApp {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String errMsg = "ERROR: argumentos no válidos\n\t" +
                "Uso: java -jar com.vaultapp.VaultApp.jar [modo]\n" +
                "Modos:\n\t" +
                "--cli: aplicación en línea de comandos\n\t" +
                "--gui: aplicación con cliente gráfico";

        List<Film> f = FilmApiDao.getInstance().searchByTitle("star", 1);
        for (Film film : f) {
            FilmDbRepository.getInstance().add(film);
        }
        f = FilmApiDao.getInstance().searchByTitle("Fight", 1);
        for (Film film : f) {
            FilmDbRepository.getInstance().add(film);
        }

        if (args.length == 0) {
            //todo
        } else if (args.length == 1 && args[0].equals("--cli")) {
            MainCLI.startApp();
        } else if (args.length == 1 && args[0].equals("--gui")) {
            MainGUI.startApp();
        } else {
            System.out.println(errMsg);
        }
    }
}
