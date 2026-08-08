package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String customerName;
    private String carModel;
    private double basePrice;
    private double taxRate;
    private double totalAmount;
    private LocalDate invoiceDate;
    private double paidAmount;

    public Invoice(String invoiceId, String customerName, String carModel, double basePrice, double taxRate, LocalDate invoiceDate) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.carModel = carModel;
        this.basePrice = basePrice;
        this.taxRate = taxRate;
        this.totalAmount = basePrice + (basePrice * taxRate / 100.0);
        this.invoiceDate = invoiceDate;
        this.paidAmount = 0.0;
    }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(double paidAmount) { this.paidAmount = paidAmount; }

    public double getRemainingBalance() { return totalAmount - paidAmount; }

    @Override
    public String toString() {
        return invoiceId + " - " + customerName + " (" + carModel + ")";
    }
}
