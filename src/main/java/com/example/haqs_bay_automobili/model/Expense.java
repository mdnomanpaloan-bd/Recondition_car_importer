package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    private String expenseId;
    private String category; // Rent, Utilities, Salaries, Maintenance, Marketing
    private String description;
    private double amount;
    private LocalDate date;

    public Expense(String expenseId, String category, String description, double amount, LocalDate date) {
        this.expenseId = expenseId;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getExpenseId() { return expenseId; }
    public void setExpenseId(String expenseId) { this.expenseId = expenseId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return expenseId + " - " + category + " ($" + amount + ")";
    }
}
