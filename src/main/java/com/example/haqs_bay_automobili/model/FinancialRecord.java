package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class FinancialRecord implements Serializable {
    private String recordId;
    private String type; // Income, Expense
    private String category;
    private String description;
    private double amount;
    private LocalDate date;
    private String reference;

    public FinancialRecord(String recordId, String type, String category, String description, double amount, LocalDate date, String reference) {
        this.recordId = recordId;
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.reference = reference;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    @Override
    public String toString() {
        return recordId + " [" + type + "] " + category + " - $" + amount;
    }
}
