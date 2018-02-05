/*
 * Copyright (c) 2018 Michael Tang, CMPUT301, University of Alberta - All Rights Reserved.
 * you may use, distribute or modify this code under terms and conditions of Code of Students  Behavior at University of Alberta.
 */

package com.example.subbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Michael on 1/25/2018.
 *
 * Form used for adding new entry and deleting or editing existing entry
 * called by MainActivity and quits to MainActivity
 */

class NewEntry extends Activity {

    private SubBook subBook;
    //fields for entering information. assumes entries are not null
    private EditText nameText;
    private EditText commentText;
    private EditText chargeText;
    private EditText dateText;

    /**
     * called when activity begins
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        subBook = new SubBook();
        subBook.loadFile(getApplicationContext());
        nameText = findViewById(R.id.nameField);
        commentText = findViewById(R.id.commentField);
        chargeText = findViewById(R.id.chargeField);
        dateText = findViewById(R.id.dateField);

        //true for edit, false for new entry
        final Boolean editFlag = getIntent().getBooleanExtra("editFlag", false);
        final int index = getIntent().getIntExtra("subscriptionIndex",0);

        //grabs information from ArrayList and display it to the user in EditText box
        if(editFlag){
            Subscription subscription = subBook.getSubscription(index);
            nameText.setText(subscription.getName());
            commentText.setText(subscription.getComment());
            chargeText.setText(subscription.getCharge());
            dateText.setText(subscription.getDate());
        }

        /**
         * DatePicker for dateText box
         *
         * References:
         * Android_coder
         * Feburary 18, 2013
         * "Datepicker: how to popup datepicker when clicked on edittext"
         * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
         *
         * Intathep
         * Feburary 18, 2013
         * "Datepicker: how to popup datepicker when clicked on edittext"
         * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
         *
         * SatanEnglish
         *  November 11, 2013
         * "Datepicker: how to popup datepicker when clicked on edittext"
         * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
         *
         */
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
                String myFormat = "yy-MM-dd"; //In which you need put here
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

        /**
         * saveButton used to save new entry or save edits
         */
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                if (editFlag) { //editing
                    subBook.set(index, new Subscription(nameText.getText().toString(),
                            commentText.getText().toString(),
                            chargeText.getText().toString(),
                            dateText.getText().toString()
                    ));
                    subBook.saveFile(getApplicationContext());
                    finish();
                } else { // new entry
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

        /**
         * for deleting old entry or quit new entry without saving
         */
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
