package com.example.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private ListView subDisplay;
    private SubBook subBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subBook = new SubBook(this);
        subDisplay = (ListView) findViewById(R.id.subList);

        subDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editEntry = new Intent(getApplicationContext(), EditEntry.class);
                startActivity(editEntry);
            }
        });



        Button newEntryButton = (Button) findViewById(R.id.newEntryButton);
        newEntryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent newEntry = new Intent(getApplicationContext(), NewEntry.class);
                newEntry.putExtra("subBook", subBook);
                startActivity(newEntry);

            }
        });
    }

    public void onStart() {
        super.onStart();
        subDisplay.setAdapter(subBook.getAdapter());
    }
}
