package com.example.midterm_mobileapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView lvHistory;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);
        btnBack   = findViewById(R.id.btnBack);

        // Get list of all numbers that have had tables generated
        List<Integer> numbers = DataRepository.getHistory();

        // Convert to string list for ListView display
        ArrayList<String> displayList = new ArrayList<>();
        for (Integer n : numbers) {
            displayList.add(String.valueOf(n));
        }

        // Bind to ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );
        lvHistory.setAdapter(adapter);

        // Return to previous screen
        btnBack.setOnClickListener(v -> finish());
    }
}
