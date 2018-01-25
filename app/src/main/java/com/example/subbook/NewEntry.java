package com.example.subbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Michael on 1/25/2018.
 */

class NewEntry extends Activity {

    private EditText nameText;
    private EditText commentText;
    private EditText chargeText;
    private EditText dateText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        Intent intent = getIntent();
        final SubBook subBook = (SubBook) intent.getSerializableExtra("subBook");

        nameText = (EditText) findViewById(R.id.nameField);
        commentText = (EditText) findViewById(R.id.commentField);
        chargeText = (EditText) findViewById(R.id.chargeField);
        dateText = (EditText) findViewById(R.id.dateField);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                subBook.newEntry(nameText.getText().toString(),
                                 commentText.getText().toString(),
                                 chargeText.getText().toString(),
                                 dateText.getText().toString()
                                );
                subBook.adapterUpdate();
                subBook.saveFile();
            }
        });
    }


}
