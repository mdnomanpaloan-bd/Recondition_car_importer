package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private String reservationId;
    private String chassisNumber;
    private String customerId;
    private String customerName;
    private LocalDate reservationDate;
    private double bookingAmount;
    private String status; // Active, Cancelled, Completed

    public Reservation(String reservationId, String chassisNumber, String customerId, String customerName, LocalDate reservationDate, double bookingAmount, String status) {
        this.reservationId = reservationId;
        this.chassisNumber = chassisNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.reservationDate = reservationDate;
        this.bookingAmount = bookingAmount;
        this.status = status;
    }

    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }

    public String getChassisNumber() { return chassisNumber; }
    public void setChassisNumber(String chassisNumber) { this.chassisNumber = chassisNumber; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public double getBookingAmount() { return bookingAmount; }
    public void setBookingAmount(double bookingAmount) { this.bookingAmount = bookingAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return reservationId + " - Car: " + chassisNumber + " (" + customerName + ")";
    }
}
