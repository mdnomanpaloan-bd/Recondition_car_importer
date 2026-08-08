package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    private String expenseId;
    private String category; // Rent, Utility, Salary, Maintenance, Marketing
    private double amount;
    private LocalDate date;

    public Expense(String expenseId, String category, double amount, LocalDate date) {
        this.expenseId = expenseId;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getExpenseId() { return expenseId; }
    public void setExpenseId(String expenseId) { this.expenseId = expenseId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
