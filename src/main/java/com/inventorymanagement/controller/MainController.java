package com.inventorymanagement.controller;

import com.inventorymanagement.model.Inventory;
import com.inventorymanagement.model.Part;
import com.inventorymanagement.model.Product;
import com.inventorymanagement.util.NotificationUtil;
import com.inventorymanagement.util.SceneChangerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the main menu view.
 */
public class MainController implements Initializable {
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
    private TextField searchPartTxt;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TextField searchProductTxt;

    /**
     * Sets the TableView and TableColumns for the parts table and products table.
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

        productsTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
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
     * Changes scene to the add part view.
     *
     * @param event ActionEvent from clicking the add button.
     * @throws IOException
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/com/inventorymanagement/add-part.fxml");
    }

    /**
     * Changes scene to the modify part view.
     * Calls the modify part controller method setFields to pass the index and part.
     * Does nothing if no part is selected.
     *
     * @param event ActionEvent from clicking the modify button.
     * @throws IOException
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        if (partsTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/inventorymanagement/modify-part.fxml"));
        loader.load();
        ModifyPartController modifyPartController = loader.getController();
        modifyPartController.setFields(partsTableView.getSelectionModel().getSelectedIndex(), partsTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes a part from the inventory.
     * Displays a confirmation pop-up before deleting.
     * Does nothing if no part is selected.
     *
     * @param event ActionEvent from clicking the delete button.
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        if (part != null && NotificationUtil.displayConfirmationAlert("Delete Part", "Are you sure you want to delete this part?")) {
            Inventory.deletePart(part);
        }
    }

    /**
     * Searches for products based on ID or name after clicking the search button.
     * All products will be shown if the search text field is empty.
     *
     * @param event ActionEvent from clicking the search button.
     */
    @FXML
    void onActionSearchProduct(ActionEvent event) {
        if (searchProductTxt.getText().isEmpty()) {
            productsTableView.setItems(Inventory.getAllProducts());
        } else {
            String searchStr = searchProductTxt.getText();
            ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

            try {
                int id = Integer.parseInt(searchStr);
                Product product = Inventory.lookupProduct(id);
                if (product != null) {
                    filteredProducts.add(product);
                }
            } catch (NumberFormatException e) {
                filteredProducts = Inventory.lookupProduct(searchStr);
            }

            if (filteredProducts.isEmpty()) {
                NotificationUtil.displayAlert(Alert.AlertType.INFORMATION, "Search Product", "No products found.");
            }

            productsTableView.setItems(filteredProducts);
        }
    }

    /**
     * Changes scene to the add product view.
     *
     * @param event ActionEvent from clicking the add button.
     * @throws IOException
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        SceneChangerUtil.changeScene(event, "/com/inventorymanagement/add-product.fxml");
    }

    /**
     * Changes scene to the modify product view.
     * Calls the modify product controller method setFields to pass the index and product.
     * Does nothing if no product is selected.
     *
     * @param event ActionEvent from clicking the modify button.
     * @throws IOException
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        if (productsTableView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/inventorymanagement/modify-product.fxml"));
        loader.load();
        ModifyProductController modifyProductController = loader.getController();
        modifyProductController.setFields(productsTableView.getSelectionModel().getSelectedIndex(), productsTableView.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes a product from the inventory.
     * Displays a confirmation pop-up before deleting.
     * If product has associated parts the product will not be deleted and an error pop-up will display.
     * Does nothing if no part is selected.
     *
     * @param event ActionEvent from clicking the delete button.
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product product = productsTableView.getSelectionModel().getSelectedItem();
        if (product != null && !product.getAllAssociatedParts().isEmpty()) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Delete Product", "Please remove associated part(s) before deleting this product");
        } else if (product != null && NotificationUtil.displayConfirmationAlert("Delete Product", "Are you sure you want to delete this product?")) {
            Inventory.deleteProduct(product);
        }
    }

    /**
     * Exits the application when the exit button is clicked.
     *
     * @param event ActionEvent from clicking the exit button.
     */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }
}