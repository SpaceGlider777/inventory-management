package com.inventorymanagement.util;

/**
 * Utility class for generating the unique IDs for parts and products.
 */
public class IdGeneratorUtil {
    public static int nextPartId = 1;
    public static int nextProductId = 1;

    /**
     * Increments the next part ID by one.
     */
    public static void incrementPartId() {
        nextPartId++;
    }

    /**
     * Increments the next product ID by one.
     */
    public static void incrementProductId() {
        nextProductId++;
    }
}
