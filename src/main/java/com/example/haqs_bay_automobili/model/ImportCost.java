package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ImportCost implements Serializable {
    private String costId;
    private String chassisNumber;
    private String lcNumber;
    private double customsDuty;
    private double freightFee;
    private double portHandlingFee;
    private LocalDate entryDate;

    public ImportCost(String costId, String chassisNumber, String lcNumber, double customsDuty, double freightFee, double portHandlingFee, LocalDate entryDate) {
        this.costId = costId;
        this.chassisNumber = chassisNumber;
        this.lcNumber = lcNumber;
        this.customsDuty = customsDuty;
        this.freightFee = freightFee;
        this.portHandlingFee = portHandlingFee;
        this.entryDate = entryDate;
    }

    public String getCostId() { return costId; }
    public void setCostId(String costId) { this.costId = costId; }

    public String getChassisNumber() { return chassisNumber; }
    public void setChassisNumber(String chassisNumber) { this.chassisNumber = chassisNumber; }

    public String getLcNumber() { return lcNumber; }
    public void setLcNumber(String lcNumber) { this.lcNumber = lcNumber; }

    public double getCustomsDuty() { return customsDuty; }
    public void setCustomsDuty(double customsDuty) { this.customsDuty = customsDuty; }

    public double getFreightFee() { return freightFee; }
    public void setFreightFee(double freightFee) { this.freightFee = freightFee; }

    public double getPortHandlingFee() { return portHandlingFee; }
    public void setPortHandlingFee(double portHandlingFee) { this.portHandlingFee = portHandlingFee; }

    public double getTotalImportCost() {
        return customsDuty + freightFee + portHandlingFee;
    }

    public LocalDate getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    @Override
    public String toString() {
        return costId + " - Car: " + chassisNumber + " ($" + getTotalImportCost() + ")";
    }
}
