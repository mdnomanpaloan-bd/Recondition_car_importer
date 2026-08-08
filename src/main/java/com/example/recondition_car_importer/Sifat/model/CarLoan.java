package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;

public class CarLoan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String applicationId;
    private String applicantName;
    private double loanAmount;
    private int durationMonths;
    private double interestRate;
    private double monthlyEmi;
    private String status; // Pending, Approved, Rejected

    public CarLoan(String applicationId, String applicantName, double loanAmount, int durationMonths, double interestRate, String status) {
        this.applicationId = applicationId;
        this.applicantName = applicantName;
        this.loanAmount = loanAmount;
        this.durationMonths = durationMonths;
        this.interestRate = interestRate;
        this.status = status;
        this.monthlyEmi = calculateEmi();
    }

    public double calculateEmi() {
        if (loanAmount <= 0 || durationMonths <= 0 || interestRate <= 0) return 0.0;
        double r = (interestRate / 100.0) / 12.0;
        int n = durationMonths;
        return (loanAmount * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public int getDurationMonths() { return durationMonths; }
    public void setDurationMonths(int durationMonths) { this.durationMonths = durationMonths; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        this.monthlyEmi = calculateEmi();
    }

    public double getMonthlyEmi() { return monthlyEmi; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
