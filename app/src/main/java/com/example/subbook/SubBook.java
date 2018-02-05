/*
 * Copyright (c) 2018 Michael Tang, CMPUT301, University of Alberta - All Rights Reserved.
 * you may use, distribute or modify this code under terms and conditions of Code of Students  Behavior at University of Alberta.
 */

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
 *
 * Container for ArrayList of Subscriptions
 * implements getters and setters
 * implements saveFile and loadFile for reading and writing to disk
 */

public class SubBook implements Serializable{
    private ArrayList<Subscription> subBook;
    private static final String FILENAME = "data.sav";
    private float totalCharge;

    /**
     * constructor, initialize empty ArrayList
     */
    public SubBook(){
        subBook = new ArrayList<>();
    }

    /**
     * Pushes new subscription to ArrayList
     * @param subscription - the subscription being added
     */
    public void add(Subscription subscription){
        subBook.add(subscription);
        totalCharge += Float.parseFloat(subscription.getCharge());
    }

    /**
     * remove a subscription from ArrayList by index
     * @param index - the index of the subscription in the ArrayList
     */
    public void remove(int index){
        totalCharge -= Float.parseFloat(subBook.get(index).getCharge());
        subBook.remove(index);
    }

    /**
     * set a subscription at an index to new values
     * @param index - the index of the subscription in the ArrayList
     * @param subscription - the new values to be saved
     */
    public void set(int index, Subscription subscription){
        totalCharge -= Float.parseFloat(subBook.get(index).getCharge());
        subBook.set(index, subscription);
        totalCharge += Float.parseFloat(subscription.getCharge());
    }

    /**
     * getter for a single subscription by index
     * @param index - the index of the subscription in the ArrayList
     * @return Subscription
     */
    public Subscription getSubscription(int index){
        return subBook.get(index);
    }

    /**
     * getter for the entire ArrayList of Subscription
     * @return ArrayList
     */
    public ArrayList<Subscription> getBook() {
        return subBook;
    }

    /**
     * getter for the total monthly cost
     * @return the monthly cost
     */
    public float getTotalCharge() {
        return totalCharge;
    }

    /**
     * serialize ArrayList and write to file
     * @param context - context of the caller
     * @throws FileNotFoundException
     * @throws IOException
     */
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

    /**
     * unserialize a file read in from disk
     * @param context - context of the caller
     * @throws FileNotFoundException
     */
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

    /**
     * iterate over ArrayList and sum up the cost from all item in the ArrayList
     */
    private void calculateTotal() {
        totalCharge = 0;
        for(Subscription subscription : subBook) {
            totalCharge += Float.parseFloat(subscription.getCharge());
        }
    }
}
