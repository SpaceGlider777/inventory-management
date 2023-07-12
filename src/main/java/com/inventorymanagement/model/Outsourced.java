package com.inventorymanagement.model;

/**
 * Object class for Outsourced part.
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructor for Outsourced part.
     *
     * @param id The uniquely generated ID.
     * @param name The part name.
     * @param price The price.
     * @param stock The stock.
     * @param min The minimum amount of stock.
     * @param max The maximum amount of stock.
     * @param companyName The company name.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Gets the company name.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName The new company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
