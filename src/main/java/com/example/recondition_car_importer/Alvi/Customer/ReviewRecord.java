package com.example.recondition_car_importer.Alvi.Customer;

import java.io.Serializable;

public class ReviewRecord implements Serializable {

    private String vehicle;
    private int rating;
    private String comment;

    public ReviewRecord(String vehicle, int rating, String comment) {
        this.vehicle = vehicle;
        this.rating = rating;
        this.comment = comment;
    }

    public String getVehicle() {
        return vehicle;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
