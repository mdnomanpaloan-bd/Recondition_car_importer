package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    private String carId;
    private String brand;
    private String model;
    private int year;
    private double originalPrice;
    private double discountPercent;
    private String status; // Available, Booked, Sold
    private String assignedRep;
    private String customerName;
    private LocalDate bookingDate;

    public Car(String carId, String brand, String model, int year, double originalPrice, String status) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.originalPrice = originalPrice;
        this.discountPercent = 0.0;
        this.status = status;
        this.assignedRep = "Unassigned";
        this.customerName = "N/A";
        this.bookingDate = LocalDate.now();
    }

    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }

    public double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }

    public double getFinalPrice() {
        return originalPrice - (originalPrice * discountPercent / 100.0);
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedRep() { return assignedRep; }
    public void setAssignedRep(String assignedRep) { this.assignedRep = assignedRep; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    @Override
    public String toString() {
        return carId + " - " + brand + " " + model + " (" + year + ")";
    }
}
