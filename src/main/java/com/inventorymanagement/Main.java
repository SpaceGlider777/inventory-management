package com.inventorymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Javadoc folder is located in the top most directory.

/**
 * The main class.
 * FUTURE ENHANCEMENT: Make certain text fields only accept certain characters.
 * An example would be the min and max fields only taking numerical characters.
 * This would lead to better user experience and prevent some typos from occurring.
 */
public class Main extends Application {

    /**
     * Sets the scene to the main menu.
     *
     * @param stage The stage.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}