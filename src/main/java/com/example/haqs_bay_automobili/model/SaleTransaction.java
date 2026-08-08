package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleTransaction implements Serializable {
    private String invoiceId;
    private String chassisNumber;
    private String customerId;
    private String customerName;
    private LocalDate saleDate;
    private double saleAmount;
    private double amountPaid;
    private double dueAmount;
    private String paymentStatus; // Paid, Partial, Unpaid

    public SaleTransaction(String invoiceId, String chassisNumber, String customerId, String customerName, LocalDate saleDate, double saleAmount, double amountPaid) {
        this.invoiceId = invoiceId;
        this.chassisNumber = chassisNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
        this.amountPaid = amountPaid;
        this.dueAmount = Math.max(0, saleAmount - amountPaid);
        this.paymentStatus = dueAmount == 0 ? "Paid" : (amountPaid > 0 ? "Partial" : "Unpaid");
    }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getChassisNumber() { return chassisNumber; }
    public void setChassisNumber(String chassisNumber) { this.chassisNumber = chassisNumber; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }

    public double getSaleAmount() { return saleAmount; }
    public void setSaleAmount(double saleAmount) { this.saleAmount = saleAmount; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        this.dueAmount = Math.max(0, saleAmount - amountPaid);
        this.paymentStatus = dueAmount == 0 ? "Paid" : (amountPaid > 0 ? "Partial" : "Unpaid");
    }

    public double getDueAmount() { return dueAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    @Override
    public String toString() {
        return invoiceId + " - " + customerName + " ($" + saleAmount + ")";
    }
}
