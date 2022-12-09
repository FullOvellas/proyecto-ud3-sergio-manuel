package com.vaultapp;

import com.vaultapp.controller.MainController;

import java.util.Locale;

public class VaultApp {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String errMsg = "ERROR: argumentos no válidos\n\t" +
                "Uso: java -jar com.vaultapp.VaultApp.jar [modo]\n" +
                "Modos:\n\t" +
                "--cli: aplicación en línea de comandos\n\t" +
                "--gui: aplicación con cliente gráfico";

        if (args.length == 0) {
            new MainController();
        } else if (args.length == 1 && args[0].equals("--cli")) {
            // MainCLI.startApp();
        } else if (args.length == 1 && args[0].equals("--gui")) {
            MainGUI.startApp();
        } else {
            System.out.println(errMsg);
        }
    }
}
