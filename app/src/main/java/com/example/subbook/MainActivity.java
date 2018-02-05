/*
 * Copyright (c) 2018 Michael Tang, CMPUT301, University of Alberta - All Rights Reserved.
 * you may use, distribute or modify this code under terms and conditions of Code of Students  Behavior at University of Alberta.
 */

package com.example.subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

/**
 * main activity of the app
 * displays list of clickable items of subscriptions and a button to add new subscriptions
 *
 */
public class MainActivity extends AppCompatActivity {

    private ListView subDisplay;        //displays subscriptions
    private SubBook subBook;            //container for subscriptions
    private SubDisplayAdapter adapter;
    private EditText total;             //displays total cost

    /**
     * Called when activity is first created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subBook = new SubBook();
        total = findViewById(R.id.totalDisplay);
        subDisplay = findViewById(R.id.subList);
        subDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent editEntry = new Intent(getApplicationContext(), NewEntry.class);
                editEntry.putExtra("editFlag", true);
                editEntry.putExtra("subscriptionIndex", index);
                startActivity(editEntry);
            }
        });

        FloatingActionButton newEntryButton = findViewById(R.id.newEntryButton);
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEntry = new Intent(getApplicationContext(), NewEntry.class);
                newEntry.putExtra("editFlag", false);
                startActivity(newEntry);
            }
        });
    }

    /**
     * called after onCreate()
     */
    public void onStart() {
        super.onStart();
        subBook.loadFile(getApplicationContext());          //check for file and load if exist
        adapter = new SubDisplayAdapter(this, R.layout.subscription_display_layout, subBook.getBook());
        subDisplay.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        total.setText(String.valueOf(subBook.getTotalCharge()));
    }

    /**
     * called when activity comes to the foreground
     * after the other activity finishes
     */
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        total.setText(String.valueOf(subBook.getTotalCharge()));
    }
}
