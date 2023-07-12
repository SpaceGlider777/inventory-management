package com.inventorymanagement.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Object class for Product.
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for Product.
     *
     * @param id The uniquely generated ID.
     * @param name The product name.
     * @param price The price.
     * @param stock The stock.
     * @param min The minimum amount of stock.
     * @param max The maximum amount of stock.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Gets the product ID.
     *
     * @return The product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the product ID.
     *
     * @param id The new product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     *
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name The new product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price The new price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock.
     *
     * @return The stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock.
     *
     * @param stock The new stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the minimum amount of stock.
     *
     * @return The minimum amount of stock.
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimum amount of stock.
     *
     * @param min The new minimum amount of stock.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the maximum amount of stock.
     *
     * @return The maximum amount of stock.
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximum amount of stock.
     *
     * @param max The new maximum amount of stock.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part to associated parts.
     *
     * @param part The part to be added.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes a part from associated parts.
     *
     * @param selectedAssociatedPart The part to be deleted.
     * @return True if the list contained the deleted part, false otherwise.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Gets the associated parts.
     *
     * @return The associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
