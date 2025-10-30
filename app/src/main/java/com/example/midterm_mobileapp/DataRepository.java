package com.example.midterm_mobileapp;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DataRepository {
    // Private constructor to prevent instantiation
    private DataRepository() {}

    // Ordered & unique numbers whose tables were generated
    private static final LinkedHashSet<Integer> historyNumbers = new LinkedHashSet<>();

    // Current table state shown on MainActivity
    private static Integer currentNumber = null;
    private static final ArrayList<String> currentTable = new ArrayList<>();

    /**
     * Generates a multiplication table (1–10) for the given number
     * and saves it as the current table. Also records the number in history.
     */
    public static void setTableFor(int number) {
        currentNumber = number;
        currentTable.clear();

        for (int i = 1; i <= 10; i++) {
            currentTable.add(number + " × " + i + " = " + (number * i));
        }

        // Record this number in the history
        historyNumbers.add(number);
    }

    /**
     * Returns the current multiplication table being displayed.
     */
    public static ArrayList<String> getCurrentTable() {
        return currentTable;
    }

    /**
     * Returns the last number for which a table was generated.
     */
    public static Integer getCurrentNumber() {
        return currentNumber;
    }

    /**
     * Deletes a row from the current table at the given index.
     * Returns the deleted string (for Toast), or null if invalid.
     */
    public static String deleteRowAt(int index) {
        if (index >= 0 && index < currentTable.size()) {
            return currentTable.remove(index);
        }
        return null;
    }

    /**
     * Clears all rows from the current table (used for “Clear All” feature).
     */
    public static void clearCurrentTable() {
        currentTable.clear();
    }

    /**
     * Returns a list of all numbers for which tables were generated.
     */
    public static List<Integer> getHistory() {
        return new ArrayList<>(historyNumbers);
    }

    /**
     * Adds a number to history manually (if needed from other parts of the app).
     */
    public static void addToHistory(int number) {
        historyNumbers.add(number);
    }

    /**
     * Clears the entire history list (for optional bonus “Clear History”).
     */
    public static void clearHistory() {
        historyNumbers.clear();
    }
}

