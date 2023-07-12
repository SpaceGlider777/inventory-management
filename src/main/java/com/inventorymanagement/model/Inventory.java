package com.inventorymanagement.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to hold the application's parts and products.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a part to the parts list.
     *
     * @param newPart The part to be added.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to the products list.
     *
     * @param newProduct The product to be added.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Searches for a part by ID.
     *
     * @param partId The ID to search for.
     * @return The part with the ID or null if not found.
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;
    }

    /**
     * Searches for a product by ID.
     *
     * @param productId The ID to search for.
     * @return The product with the ID or null if not found.
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    /**
     * Searches for parts by name.
     *
     * @param partName The name to search for.
     * @return The list of parts that contain the name.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                filteredList.add(part);
            }
        }

        return filteredList;
    }

    /**
     * Searches for products by name.
     *
     * @param productName The name to search for.
     * @return The list of products that contain the name.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    /**
     * Updates a part in inventory.
     *
     * @param index The index of the part in the inventory part list.
     * @param selectedPart The updated part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a product in inventory.
     *
     * @param index The index of the product in the inventory product list.
     * @param newProduct The updated product.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a part in inventory.
     *
     * @param selectedPart The part to be deleted.
     * @return True if the list contained the deleted part, false otherwise.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes a product in inventory.
     *
     * @param selectedProduct The product to be deleted.
     * @return True if the list contained the deleted product, false otherwise.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Gets the list of parts.
     *
     * @return The list of parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets the list of products.
     *
     * @return The list of products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
