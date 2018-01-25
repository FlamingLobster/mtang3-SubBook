package com.example.subbook;

import android.content.Context;
import android.widget.ArrayAdapter;
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
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Michael on 1/25/2018.
 */

public class SubBook implements Serializable{
    private static final String FILENAME = "data.sav";
    private ArrayList<Subscription> subs;
    private ArrayAdapter<Subscription> adapter;
    private Context context;


    public SubBook(Context context){
        this.subs = new ArrayList<Subscription>();
        this.context = context;
        adapter = new ArrayAdapter<Subscription>(this.context, R.layout.list_item, this.subs);

        loadFile();
    }

    public ArrayAdapter<Subscription> getAdapter() {
        return adapter;
    }

    private void loadFile() {
        try {
            FileInputStream fileIn = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));

            Gson gson = new Gson();
            Type subToken = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subs = gson.fromJson(in, subToken);

        } catch (FileNotFoundException e) {
            subs = new ArrayList<Subscription>();
        }
    }

    public void saveFile() {
        try {
            FileOutputStream fileOut = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fileOut));

            Gson gson = new Gson();
            gson.toJson(subs, out);
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void newEntry(String nameText, String commentText, String chargeText, String dateText) {
        subs.add(new Subscription(nameText, commentText, chargeText, dateText));
    }

    public void adapterUpdate() {
        adapter.notifyDataSetChanged();
    }
}
