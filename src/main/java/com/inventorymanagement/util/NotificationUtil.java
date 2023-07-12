package com.inventorymanagement.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Utility class for displaying pop-ups.
 */
public class NotificationUtil {

    /**
     * Displays a pop-up.
     *
     * @param alertType The alert type.
     * @param title The title.
     * @param contentText The content text.
     */
    public static void displayAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation pop-up.
     *
     * @param title The title.
     * @param contentText The content text.
     * @return True if the user clicked OK, false otherwise.
     */
    public static boolean displayConfirmationAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
