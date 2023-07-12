package com.inventorymanagement.controller;

import com.inventorymanagement.model.Inventory;
import com.inventorymanagement.model.Part;
import com.inventorymanagement.model.Product;
import com.inventorymanagement.util.NotificationUtil;
import com.inventorymanagement.util.SceneChangerUtil;
import com.inventorymanagement.util.ValidatorUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the modify product view.
 */
public class ModifyProductController implements Initializable {
    private int index;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> productPartsTableView;
    @FXML
    private TableColumn<Part, Integer> productPartIdCol;
    @FXML
    private TableColumn<Part, String> productPartNameCol;
    @FXML
    private TableColumn<Part, Integer> productPartInventoryCol;
    @FXML
    private TableColumn<Part, Double> productPartPriceCol;
    @FXML
    private TextField searchPartTxt;

    /**
     * Sets the index, each text field, and associated parts.
     *
     * @param index The index of the product in the inventory product list.
     * @param product The product to be modified.
     */
    public void setFields(int index, Product product) {
        this.index = index;

        for (Part part : product.getAllAssociatedParts()) {
            associatedParts.add(part);
        }

        idTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        invTxt.setText(String.valueOf(product.getStock()));
        priceTxt.setText(String.valueOf(product.getPrice()));
        maxTxt.setText(String.valueOf(product.getMax()));
        minTxt.setText(String.valueOf(product.getMin()));
    }

    /**
     * Sets the TableView and TableColumns for the parts table.
     * LOGICAL ERROR: Previously used product.getAllAssociatedParts() to set productPartsTableView items in setFields method.
     * This resulted in associated parts modifications getting saved even though cancel was clicked.
     * To fix this a new associated parts list was created to copy the product's associated parts.
     * Then a new product would be created to replace the old product.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productPartsTableView.setItems(associatedParts);
        productPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Searches for parts based on ID or name after clicking the search button.
     * All parts will be shown if the search text field is empty.
     *
     * @param event ActionEvent from clicking the search button.
     */
    @FXML
    void onActionSearchPart(ActionEvent event) {
        if (searchPartTxt.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getAllParts());
        } else {
            String searchStr = searchPartTxt.getText();
            ObservableList<Part> filteredParts = FXCollections.observableArrayList();

            try {
                int id = Integer.parseInt(searchStr);
                Part part = Inventory.lookupPart(id);
                if (part != null) {
                    filteredParts.add(part);
                }
            } catch (NumberFormatException e) {
                filteredParts = Inventory.lookupPart(searchStr);
            }

            if (filteredParts.isEmpty()) {
                NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Search Part", "No parts found.");
            }

            partsTableView.setItems(filteredParts);
        }
    }

    /**
     * Adds a part to associated parts when add is clicked.
     * Does nothing if no part is selected.
     *
     * @param event ActionEvent from clicking the add button.
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedParts.add(selectedPart);
        }
    }

    /**
     * Removes a part from associated parts when remove is clicked.
     * Displays a confirmation pop-up before removing.
     * Does nothing if no part is selected.
     *
     * @param event ActionEvent from clicking the remove button.
     */
    @FXML
    void onActionRemovePart(ActionEvent event) {
        Part selectedPart = productPartsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null && NotificationUtil.displayConfirmationAlert("Remove Part", "Are you sure you want to remove this part?")) {
            associatedParts.remove(selectedPart);
        }
    }

    /**
     * Updates the product in inventory when save is clicked.
     * If fields are blank or have errors an error pop-up will display.
     * Returns to main menu after saving.
     *
     * @param event ActionEvent from clicking the save button.
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        if (!ValidatorUtil.validateProduct(nameTxt.getText(), invTxt.getText(), priceTxt.getText(), minTxt.getText(), maxTxt.getText())) {
            return;
        }

        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        Product product = new Product(id, name, price, stock, min, max);

        for (Part part : associatedParts) {
            product.addAssociatedPart(part);
        }

        Inventory.updateProduct(index, product);
        SceneChangerUtil.changeScene(event, "/com/inventorymanagement/main-view.fxml");
    }

    /**
     * Returns to the main menu when cancel is clicked.
     *
     * @param event ActionEvent from clicking the cancel button.
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/com/inventorymanagement/main-view.fxml");
    }
}
