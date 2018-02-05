/*
 * Copyright (c) 2018 Michael Tang, CMPUT301, University of Alberta - All Rights Reserved.
 * you may use, distribute or modify this code under terms and conditions of Code of Students  Behavior at University of Alberta.
 */

package com.example.subbook;

import java.io.Serializable;

/**
 * Created by Michael on 1/25/2018.
 *
 * Container for a single subscription
 * implements typical getters
 *
 * @see SubBook
 */

class Subscription implements Serializable{
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

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCharge() {
        return charge;
    }

    public String getComment() { return comment; }
}
