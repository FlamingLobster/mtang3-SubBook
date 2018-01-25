package com.example.subbook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Michael on 1/25/2018.
 */

class EditEntry extends Activity {

    private EditText name;
    private EditText comment;
    private EditText charge;
    private EditText date;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
    }
}
