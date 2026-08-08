package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {
    private String chassisNumber;
    private String brand;
    private String model;
    private int year;
    private double purchasePrice;
    private double importCost;
    private double sellingPrice;
    private String status; // Available, Reserved, Sold
    private String assignedRep;
    private LocalDate stockDate;

    public Car(String chassisNumber, String brand, String model, int year, double purchasePrice, double importCost, double sellingPrice, String status) {
        this.chassisNumber = chassisNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.importCost = importCost;
        this.sellingPrice = sellingPrice;
        this.status = status;
        this.assignedRep = "Unassigned";
        this.stockDate = LocalDate.now();
    }

    public String getChassisNumber() { return chassisNumber; }
    public void setChassisNumber(String chassisNumber) { this.chassisNumber = chassisNumber; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getImportCost() { return importCost; }
    public void setImportCost(double importCost) { this.importCost = importCost; }

    public double getTotalCost() { return purchasePrice + importCost; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedRep() { return assignedRep; }
    public void setAssignedRep(String assignedRep) { this.assignedRep = assignedRep; }

    public LocalDate getStockDate() { return stockDate; }
    public void setStockDate(LocalDate stockDate) { this.stockDate = stockDate; }

    @Override
    public String toString() {
        return chassisNumber + " - " + brand + " " + model + " (" + status + ")";
    }
}
