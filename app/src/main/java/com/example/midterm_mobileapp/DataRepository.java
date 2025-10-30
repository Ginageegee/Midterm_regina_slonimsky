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
     * Returns a list of all numbers for which tables were generated.
     */
    public static List<Integer> getHistory() {
        return new ArrayList<>(historyNumbers);
    }


    public static void addToHistory(int number) {
        historyNumbers.add(number);
    }

}

