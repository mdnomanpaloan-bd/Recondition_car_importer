package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;

public class EmployeeSalary implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String name;
    private double baseSalary;
    private double commissionRate; // Percentage e.g. 5.0 for 5%
    private int salesCount;
    private double totalPay;

    public EmployeeSalary(String employeeId, String name, double baseSalary, double commissionRate, int salesCount) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.commissionRate = commissionRate;
        this.salesCount = salesCount;
        this.totalPay = calculateTotalPay();
    }

    public double calculateTotalPay() {
        return baseSalary + (salesCount * commissionRate * 100);
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
        this.totalPay = calculateTotalPay();
    }

    public double getCommissionRate() { return commissionRate; }
    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
        this.totalPay = calculateTotalPay();
    }

    public int getSalesCount() { return salesCount; }
    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
        this.totalPay = calculateTotalPay();
    }

    public double getTotalPay() { return totalPay; }
}
