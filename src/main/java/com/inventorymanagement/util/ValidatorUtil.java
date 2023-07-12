package com.inventorymanagement.util;

import javafx.scene.control.Alert;

/**
 * Utility class to validate parts and products.
 */
public class ValidatorUtil {

    /**
     * Validates a part.
     * Displays an error pop-up if the part is not valid.
     *
     * @param isInHouse True if part is InHouse, false if part is Outsourced.
     * @param name The part name.
     * @param stockStr The stock as a string.
     * @param priceStr The price as a string.
     * @param minStr The minimum stock as a string.
     * @param maxStr The maximum stock as a string.
     * @param machineIdStr The machine ID as a string.
     * @return True if the part is valid, false otherwise.
     */
    public static boolean validatePart(boolean isInHouse, String name, String stockStr, String priceStr, String minStr, String maxStr, String machineIdStr) {
        boolean isValid = true;
        StringBuilder sb = new StringBuilder();

        if (name.isBlank()) {
            sb.append("Name is blank\n");
            isValid = false;
        }

        try {
            Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            sb.append("Inventory must be an integer\n");
            isValid = false;
        }

        try {
            Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            sb.append("Price must be a double\n");
            isValid = false;
        }

        try {
            Integer.parseInt(minStr);
        } catch (NumberFormatException e) {
            sb.append("Minimum must be an integer\n");
            isValid = false;
        }

        try {
            Integer.parseInt(maxStr);
        } catch (NumberFormatException e) {
            sb.append("Maximum must be an integer\n");
            isValid = false;
        }

        try {
            int stock = Integer.parseInt(stockStr);
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);

            if (max < min) {
                sb.append("Maximum is less than minimum\n");
                isValid = false;
            }

            if (stock < min) {
                sb.append("Inventory is less than minimum\n");
                isValid = false;
            }

            if (stock > max) {
                sb.append("Inventory is greater than maximum\n");
                isValid = false;
            }
        } catch (NumberFormatException e) { }

        if (isInHouse) {
            try {
                Integer.parseInt(machineIdStr);
            } catch (NumberFormatException e) {
                sb.append("Machine ID must be an integer\n");
                isValid = false;
            }
        } else {
            if (machineIdStr.isBlank()) {
                sb.append("Company name is blank\n");
                isValid = false;
            }
        }

        if (!isValid) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Error(s) Detected", sb.toString());
        }

        return isValid;
    }

    /**
     * Validates a product.
     * Displays an error pop-up if the product is not valid.
     *
     * @param name The product name.
     * @param stockStr The stock as a string.
     * @param priceStr The price as a string.
     * @param minStr The minimum stock as a string.
     * @param maxStr The maximum stock as a string.
     * @return True if the product is valid, false otherwise.
     */
    public static boolean validateProduct(String name, String stockStr, String priceStr, String minStr, String maxStr) {
        boolean isValid = true;
        StringBuilder sb = new StringBuilder();

        if (name.isBlank()) {
            sb.append("Name is blank\n");
            isValid = false;
        }

        try {
            Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            sb.append("Inventory must be an integer\n");
            isValid = false;
        }

        try {
            Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            sb.append("Price must be a double\n");
            isValid = false;
        }

        try {
            Integer.parseInt(minStr);
        } catch (NumberFormatException e) {
            sb.append("Minimum must be an integer\n");
            isValid = false;
        }

        try {
            Integer.parseInt(maxStr);
        } catch (NumberFormatException e) {
            sb.append("Maximum must be an integer\n");
            isValid = false;
        }

        try {
            int stock = Integer.parseInt(stockStr);
            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);

            if (max < min) {
                sb.append("Maximum is less than minimum\n");
                isValid = false;
            }

            if (stock < min) {
                sb.append("Inventory is less than minimum\n");
                isValid = false;
            }

            if (stock > max) {
                sb.append("Inventory is greater than maximum\n");
                isValid = false;
            }
        } catch (NumberFormatException e) { }

        if (!isValid) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Error(s) Detected", sb.toString());
        }

        return isValid;
    }
}
