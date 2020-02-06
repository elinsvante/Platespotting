package com.example.platespotting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private ListView listViewPlates;
    private List<String> foundPlates = new ArrayList<>();
    TextView currentPlateSearch;
    ConstraintLayout mainLayout;
    int plateNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        listViewPlates = findViewById(R.id.listViewPlates);
        currentPlateSearch = findViewById(R.id.textSearchPlatter);
        mainLayout = findViewById(R.id.mainLayout);
    }

    private Adapter getAdapter() {
        return new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , foundPlates);
    }

    public void updateSearch(View view) {
        updatePlateList();
        updatePlateSearch();
    }

    private void updatePlateSearch() {
        if (plateNumber <= 999) {
            plateNumber++;
            String prefixNumber = String.format("%03d", plateNumber);
            currentPlateSearch.setText("You are looking for: " + prefixNumber);
        }
        else {
            plateNumber = 1;
            foundPlates.clear();
            listViewPlates.setAdapter((ArrayAdapter)getAdapter());

            Toast.makeText(getApplicationContext(), "Congratulations! You have found all plates!", Toast.LENGTH_LONG).show();
        }
    }

    private void updatePlateList() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String prefixNumber = String.format("%03d", plateNumber);
        foundPlates.add(prefixNumber + " - seen " + dateFormat.format(date));
        listViewPlates.setAdapter((ArrayAdapter)getAdapter());

        Snackbar.make(mainLayout, "You have added the plate to your list!", Snackbar.LENGTH_LONG).show();
    }
}
