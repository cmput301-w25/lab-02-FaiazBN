package com.example.listycity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addCityButton = findViewById(R.id.AddCityButton);
        Button deleteCityButton = findViewById(R.id.DeleteCityButton);

        addCityButton.setOnClickListener(v -> {
            EditText inputField = new EditText(this);
            inputField.setHint("Enter city name");

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Add City")
                    .setMessage("Enter the name of the city:")
                    .setView(inputField)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = inputField.getText().toString().trim();
                        if (!cityName.isEmpty()) {
                            dataList.add(cityName);
                            cityAdapter.notifyDataSetChanged();
                            Toast.makeText(this, cityName + " added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "City name cannot be empty!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // Delete City functionality
        deleteCityButton.setOnClickListener(v -> {
            int position = cityList.getCheckedItemPosition();
            if (position >= 0 && position < dataList.size()) {
                String removedCity = dataList.remove(position);
                cityAdapter.notifyDataSetChanged(); // Refresh the list
                Toast.makeText(this, removedCity + " deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Select a city to delete!", Toast.LENGTH_SHORT).show();
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}