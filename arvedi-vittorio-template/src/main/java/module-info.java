module com.arvedi.app {

    /**
     * Declares that this module depends on JavaFX modules.
     *
     * javafx.controls → buttons, labels, layout controls
     * javafx.fxml     → loading graphical interfaces from FXML files
     */
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.logging;

    /**
     * JavaFX (FXML Loader) needs reflective access to the GUI controller package.
     * Without this line, JavaFX cannot create GuiController from the FXML file.
     */
    opens com.arvedi.view.gui to javafx.fxml;

    /**
     * These packages are exported so they can be used outside this module.
     * (For example by other modules, or by JavaFX components.)
     */
    exports com.arvedi.controller;
    exports com.arvedi.model;
    exports com.arvedi.view.cli;
    exports com.arvedi.view.gui;
}