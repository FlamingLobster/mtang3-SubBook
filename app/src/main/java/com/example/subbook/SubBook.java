package com.example.subbook;

import android.content.Context;

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
 * Created by Michael on 2/4/2018.
 */

public class SubBook implements Serializable{
    private ArrayList<Subscription> subBook;
    private static final String FILENAME = "data.sav";
    private float totalCharge;

    public SubBook(){
        subBook = new ArrayList<>();
    }

    public void add(Subscription subscription){
        subBook.add(subscription);
        totalCharge += Float.parseFloat(subscription.getCharge());
    }

    public void remove(int index){
        totalCharge -= Float.parseFloat(subBook.get(index).getCharge());
        subBook.remove(index);
    }

    public void set(int index, Subscription subscription){
        totalCharge -= Float.parseFloat(subBook.get(index).getCharge());
        subBook.set(index, subscription);
        totalCharge += Float.parseFloat(subscription.getCharge());
    }

    public Subscription getSubscription(int index){
        return subBook.get(index);
    }

    public ArrayList<Subscription> getBook() {
        return subBook;
    }

    public float getTotalCharge(){
        return totalCharge;
    }

    public void saveFile(Context context) {
        try {
            FileOutputStream fileOut = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fileOut));

            Gson gson = new Gson();
            gson.toJson(subBook, out);
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(Context context) {
        try {
            FileInputStream fileIn = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));

            Gson gson = new Gson();
            Type subToken = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subBook = gson.fromJson(in, subToken);

            calculateTotal();
        } catch (FileNotFoundException e) {
            subBook = new ArrayList<Subscription>();
        }
    }

    private void calculateTotal() {
        totalCharge = 0;
        for(Subscription subscription : subBook) {
            totalCharge += Float.parseFloat(subscription.getCharge());
        }
    }
}
