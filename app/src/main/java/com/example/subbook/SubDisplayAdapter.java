/*
 * Copyright (c) 2018 Michael Tang, CMPUT301, University of Alberta - All Rights Reserved.
 * you may use, distribute or modify this code under terms and conditions of Code of Students  Behavior at University of Alberta.
 */

package com.example.subbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Michael on 2/1/2018.
 *
 * Reference:
 * Rakhita
 * November 17, 2011
 * "Custom adapter for list view"
 * https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
 *
 * custom adapter for displaying list of subscriptions
 */

public class SubDisplayAdapter extends ArrayAdapter<Subscription> {

    private int resource;

    public SubDisplayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(resource, null);
        }

        Subscription subscription = getItem(position);

        if (subscription != null){
            TextView name = convertView.findViewById(R.id.name_display);
            TextView date = convertView.findViewById(R.id.date_display);
            TextView charge = convertView.findViewById(R.id.charge_display);

            name.setText(subscription.getName());
            date.setText(subscription.getDate());
            charge.setText("$"+subscription.getCharge());
        }

        return convertView;
    }
}
