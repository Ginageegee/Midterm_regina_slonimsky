package com.example.midterm_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private Button btnGenerate;
    private Button btnHistory; // 👈 add this
    private ListView lvResults;

    private final ArrayList<String> tableItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etNumber   = findViewById(R.id.etNumber);
        btnGenerate= findViewById(R.id.btnGenerate);
        lvResults  = findViewById(R.id.lvResults);
        btnHistory = findViewById(R.id.btnHistory); // 👈 find it

        // Set up ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableItems);
        lvResults.setAdapter(adapter);

        // Generate table
        btnGenerate.setOnClickListener(v -> {
            String input = etNumber.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
                return;
            }

            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
                return;
            }

            tableItems.clear();
            for (int i = 1; i <= 10; i++) {
                tableItems.add(number + " × " + i + " = " + (number * i));
            }
            adapter.notifyDataSetChanged();

            DataRepository.addToHistory(number);
        });


        lvResults.setOnItemClickListener((parent, view, position, id) -> {
            if (position < 0 || position >= tableItems.size()) return;

            String row = tableItems.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Delete row?")
                    .setMessage("Do you want to delete:\n" + row) // (tiny fix to your string)
                    .setPositiveButton("Delete", (dialog, which) -> {
                        tableItems.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Deleted: " + row, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });


        if (btnHistory != null) {
            btnHistory.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_history) {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


