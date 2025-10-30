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

    public static Integer getCurrentNumber() {
        return currentNumber;
    }


    public static String deleteRowAt(int index) {
        if (index >= 0 && index < currentTable.size()) {
            return currentTable.remove(index);
        }
        return null;
    }


    public static void clearCurrentTable() {
        currentTable.clear();
    }

    /**
     * Returns a list of all numbers for which tables were generated.
     */
    public static List<Integer> getHistory() {
        return new ArrayList<>(historyNumbers);
    }


    public static void addToHistory(int number) {
        historyNumbers.add(number);
    }

}

