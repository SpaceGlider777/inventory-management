package com.inventorymanagement.controller;

import com.inventorymanagement.model.InHouse;
import com.inventorymanagement.model.Inventory;
import com.inventorymanagement.model.Outsourced;
import com.inventorymanagement.model.Part;
import com.inventorymanagement.util.SceneChangerUtil;
import com.inventorymanagement.util.ValidatorUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Controller class for the modify part view.
 */
public class ModifyPartController {
    private int index;
    private boolean isInHouse;
    @FXML
    private RadioButton inHouseBtn;
    @FXML
    private RadioButton outsourcedBtn;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField machineIdTxt;
    @FXML
    private Label machineIdLbl;

    /**
     * Sets the index of the part and each text field value.
     *
     * @param index The index of the part in the inventory parts list.
     * @param part The part to be modified.
     */
    public void setFields(int index, Part part) {
        this.index = index;

        if (part instanceof InHouse) {
            isInHouse = true;
            inHouseBtn.setSelected(true);
            machineIdLbl.setText("Machine ID");
            machineIdTxt.setText(String.valueOf(((InHouse)part).getMachineId()));
        } else {
            isInHouse = false;
            outsourcedBtn.setSelected(true);
            machineIdLbl.setText("Company Name");
            machineIdTxt.setText(((Outsourced)part).getCompanyName());
        }

        idTxt.setText(String.valueOf(part.getId()));
        nameTxt.setText(part.getName());
        priceTxt.setText(String.valueOf(part.getPrice()));
        invTxt.setText(String.valueOf(part.getStock()));
        maxTxt.setText(String.valueOf(part.getMax()));
        minTxt.setText(String.valueOf(part.getMin()));
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
     * Updates the part in inventory when save is clicked.
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
            Inventory.updatePart(index, inHouse);
        } else {
            Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, machineIdTxt.getText());
            Inventory.updatePart(index, outsourced);
        }

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
