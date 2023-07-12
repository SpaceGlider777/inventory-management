package com.inventorymanagement.model;

/**
 * Object class for InHouse part.
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor for InHouse part.
     *
     * @param id The uniquely generated ID.
     * @param name The part name.
     * @param price The price.
     * @param stock The stock.
     * @param min The minimum amount of stock.
     * @param max The maximum amount of stock.
     * @param machineId The machine ID.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Gets the machine ID.
     *
     * @return The machine ID.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets the machine ID.
     *
     * @param machineId The new machine ID.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
