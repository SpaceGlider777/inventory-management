package com.inventorymanagement.controller;

import com.inventorymanagement.model.InHouse;
import com.inventorymanagement.model.Inventory;
import com.inventorymanagement.model.Outsourced;
import com.inventorymanagement.util.IdGeneratorUtil;
import com.inventorymanagement.util.SceneChangerUtil;
import com.inventorymanagement.util.ValidatorUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the add part view.
 */
public class AddPartController implements Initializable {
    private boolean isInHouse = true;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private Label machineIdLbl;
    @FXML
    private TextField machineIdTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;

    /**
     * Sets the uniquely generated part ID in the text field.
     *
     * @param url Unused default parameter.
     * @param resourceBundle Unused default parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idTxt.setText(String.valueOf(IdGeneratorUtil.nextPartId));
    }

    /**
     * Changes the last field lable to Machine ID when the In-House radio button is clicked.
     *
     * @param event ActionEvent from clicking the In-House radio button.
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        isInHouse = true;
        machineIdLbl.setText("Machine ID");
    }

    /**
     * Changes the last field lable to Company Name when the Outsourced radio button is clicked.
     *
     * @param event ActionEvent from clicking the Outsourced radio button.
     */
    @FXML
    void onActionOutsourced(ActionEvent event) {
        isInHouse = false;
        machineIdLbl.setText("Company Name");
    }

    /**
     * Adds the part to the inventory when save is clicked.
     * If fields are blank or have errors an error pop-up will display.
     * Returns to the main menu after saving.
     *
     * @param event ActionEvent from clicking the save button.
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        if (!ValidatorUtil.validatePart(isInHouse, nameTxt.getText(), invTxt.getText(), priceTxt.getText(), minTxt.getText(), maxTxt.getText(), machineIdTxt.getText())) {
            return;
        }

        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());

        if (isInHouse) {
            InHouse inHouse = new InHouse(id, name, price, stock, min, max, Integer.parseInt(machineIdTxt.getText()));
            Inventory.addPart(inHouse);
        } else {
            Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, machineIdTxt.getText());
            Inventory.addPart(outsourced);
        }

        IdGeneratorUtil.incrementPartId();
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
