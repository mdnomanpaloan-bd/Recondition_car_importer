package com.example.recondition_car_importer.Alvi.Customer;

import java.io.Serializable;

public class CarList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String carID;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private double price;
    private String status;
    private String condition;

    public CarList() {
    }

    public CarList(String carID, String make, String model,
                   int year, int mileage,
                   double price, String status,
                   String condition) {

        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.status = status;
        this.condition = condition;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "CarList{" +
                "carID='" + carID + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
