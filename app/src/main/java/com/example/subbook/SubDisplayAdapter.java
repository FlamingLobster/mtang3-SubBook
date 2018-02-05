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
<<<<<<< HEAD
 * https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
=======
>>>>>>> d8dea50af174b168e9eb35ca37af7c370b017d24
 */

public class SubDisplayAdapter extends ArrayAdapter<Subscription> {

    private int resource;

    public SubDisplayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(resource, null);
        }

        Subscription p = getItem(position);

        if (p != null){
            TextView name = v.findViewById(R.id.name_display);
            TextView date = v.findViewById(R.id.date_display);
            TextView charge = v.findViewById(R.id.charge_display);

            name.setText(p.getName());
            date.setText(p.getDate());
            charge.setText(p.getCharge());
        }

        return v;
    }
}
