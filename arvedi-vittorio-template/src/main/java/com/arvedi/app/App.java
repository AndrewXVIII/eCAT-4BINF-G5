package com.arvedi.app;

import com.arvedi.controller.AppController;
import com.arvedi.view.cli.CliView;
import com.arvedi.view.gui.GuiApp;
import javafx.application.Application;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            AppController controller = new AppController();

            while (true) {
            System.out.println("\n=== GESTIONALE CABINE ===");
            System.out.println("1. Avvia CLI");
            System.out.println("2. Avvia GUI");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    new CliView(controller).start();
                    return;
                case "2":
                    GuiApp.setControllerProvider(() -> controller);
                    Application.launch(GuiApp.class, args);
                    return;
                case "0":
                    System.out.println("Arrivederci");
                    return;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        }
    }
    }
}