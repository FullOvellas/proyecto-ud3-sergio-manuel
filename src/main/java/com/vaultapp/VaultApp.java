package com.vaultapp;

public class VaultApp {

    public static void main(String[] args) {

        String errMsg = "ERROR: argumentos no válidos\n\t" +
                "Uso: java -jar com.vaultapp.VaultApp.jar [modo]\n" +
                "Modos:\n\t" +
                "--cli: aplicación en línea de comandos\n\t" +
                "--gui: aplicación con cliente gráfico";

        if (args.length == 0) {
            // MainGUI.startApp();
        } else if (args.length == 1 && args[0].equals("--cli")) {
            // MainCLI.startApp();
        } else if (args.length == 1 && args[0].equals("--gui")) {
            MainGUI.startApp();
        } else {
            System.out.println(errMsg);
        }
    }
}
