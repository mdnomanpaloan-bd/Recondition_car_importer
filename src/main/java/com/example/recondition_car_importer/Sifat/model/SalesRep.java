package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;

public class SalesRep implements Serializable {
    private static final long serialVersionUID = 1L;

    private String repId;
    private String name;
    private int carsSold;
    private double revenueGenerated;

    public SalesRep(String repId, String name, int carsSold, double revenueGenerated) {
        this.repId = repId;
        this.name = name;
        this.carsSold = carsSold;
        this.revenueGenerated = revenueGenerated;
    }

    public String getRepId() { return repId; }
    public void setRepId(String repId) { this.repId = repId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCarsSold() { return carsSold; }
    public void setCarsSold(int carsSold) { this.carsSold = carsSold; }

    public double getRevenueGenerated() { return revenueGenerated; }
    public void setRevenueGenerated(double revenueGenerated) { this.revenueGenerated = revenueGenerated; }

    @Override
    public String toString() { return name; }
}
