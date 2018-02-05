package com.example.subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView subDisplay;
    private SubBook subBook;
    private SubDisplayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subBook = new SubBook();
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

    public void onStart() {
        super.onStart();
        subBook.loadFile(getApplicationContext());
        adapter = new SubDisplayAdapter(this, R.layout.subscription_display_layout, subBook.getBook());
        subDisplay.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
