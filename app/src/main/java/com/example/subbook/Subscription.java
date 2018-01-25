package com.example.subbook;

import android.widget.EditText;

import java.sql.Date;

/**
 * Created by Michael on 1/25/2018.
 */

class Subscription {
    private String name;
    private String date;
    private String charge;
    private String comment;

    public Subscription(String nameText, String commentText, String chargeText, String dateText) {
        this.name = nameText;
        this.comment = commentText;
        this.charge = chargeText;
        this.date = dateText;
    }
}
