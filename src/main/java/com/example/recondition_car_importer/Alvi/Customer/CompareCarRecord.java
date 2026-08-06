package com.example.recondition_car_importer.Alvi.Customer;

public class CompareCarRecord {

    private String feature;
    private String car1;
    private String car2;

    public CompareCarRecord(String feature, String car1, String car2) {
        this.feature = feature;
        this.car1 = car1;
        this.car2 = car2;
    }

    public String getFeature() {
        return feature;
    }

    public String getCar1() {
        return car1;
    }

    public String getCar2() {
        return car2;
    }
}
