module com.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.inventorymanagement to javafx.fxml;
    exports com.inventorymanagement;
    exports com.inventorymanagement.controller;
    exports com.inventorymanagement.model;
    opens com.inventorymanagement.controller to javafx.fxml;
}