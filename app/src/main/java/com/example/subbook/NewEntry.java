package com.example.subbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Michael on 1/25/2018.
 */

class NewEntry extends Activity {

    private SubBook subBook;
    private EditText nameText;
    private EditText commentText;
    private EditText chargeText;
    private EditText dateText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        subBook = new SubBook();
        subBook.loadFile(getApplicationContext());
        nameText = findViewById(R.id.nameField);
        commentText = findViewById(R.id.commentField);
        chargeText = findViewById(R.id.chargeField);
        dateText = findViewById(R.id.dateField);

        final Boolean editFlag = getIntent().getBooleanExtra("editFlag", false);
        final int index = getIntent().getIntExtra("subscriptionIndex",0);

        //https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateText.setText(sdf.format(myCalendar.getTime()));
            }
        };

            dateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                if(editFlag) {
                    subBook.set(index, new Subscription(nameText.getText().toString(),
                                                        commentText.getText().toString(),
                                                        chargeText.getText().toString(),
                                                        dateText.getText().toString()
                    ));
                    subBook.saveFile(getApplicationContext());
                    finish();
                } else {
                    subBook.add(new Subscription(nameText.getText().toString(),
                                                 commentText.getText().toString(),
                                                 chargeText.getText().toString(),
                                                 dateText.getText().toString()
                    ));
                    subBook.saveFile(getApplicationContext());
                    finish();
                }
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                if(editFlag){
                    System.out.println("index"+index);
                    subBook.remove(index);
                    subBook.saveFile(getApplicationContext());
                    finish();
                } else {
                    finish();
                }
            }
        });
    }
}
