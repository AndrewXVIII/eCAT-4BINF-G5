package com.arvedi.view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.Supplier;

import com.arvedi.controller.AppController;

/**
 * This class is the main entry point for the JavaFX GUI.
 *
 * Responsibilities:
 *  - Load the FXML file that defines the graphical layout
 *  - Create the Scene and Stage (window)
 *  - Provide the correct Controller instance to JavaFX
 *
 * It uses a Supplier<AppController> so that the launcher (App.java)
 * can decide how to create a fresh Controller and Model.
 */
public class GuiApp extends Application {

    // A provider (factory) for AppController instances
    private static Supplier<AppController> controllerProvider;

    /** Called by App.java before launching the GUI */
    public static void setControllerProvider(Supplier<AppController> provider) {
        controllerProvider = provider;
    }

    /**
     * JavaFX entry point.
     * Called automatically when the GUI starts.
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Load the FXML layout file
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(getClass().getResource("/view/gui/main.fxml"))
        );

        /**
         * Provide controllers to JavaFX.
         *
         * If the requested controller is GuiController,
         * we give it an AppController created using the provider.
         */
        loader.setControllerFactory(cls -> {
            if (cls == GuiController.class) {
                return new GuiController(controllerProvider.get());
            }
            try {
                // For any other JavaFX controller
                return cls.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Create the scene from the FXML
        Scene scene = new Scene(loader.load());

        // Load and apply the stylesheet CSS
        scene.getStylesheets().add(
                Objects.requireNonNull(
                    getClass().getResource("/view/gui/styles.css")
                ).toExternalForm()
        );

        // Set up the main window
        stage.setTitle("Gestionale Cabine");
        stage.setScene(scene);
        stage.show();
    }
}