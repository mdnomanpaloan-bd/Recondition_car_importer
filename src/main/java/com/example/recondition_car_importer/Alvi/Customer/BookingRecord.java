package com.example.recondition_car_importer.Alvi.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class BookingRecord implements Serializable {

    private String fullName;
    private String nid;
    private String phoneNumber;
    private LocalDate pickupDate;

    private String carID;
    private String carName;

    private String paymentMethod;
    private double finalCost;
    private String bookingID;
    private LocalDate bookingDate;
    private String status;
    private String email;
    private String address;
    private Integer purchaseCount;
    private LocalDate ExpectedDelivery;

    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public BookingRecord() {
    }

    public BookingRecord(String fullName, String nid, String phoneNumber,
                         LocalDate pickupDate, String carID, String carName,
                         String paymentMethod, double finalCost) {

        this.fullName = fullName;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
        this.pickupDate = pickupDate;
        this.carID = carID;
        this.carName = carName;
        this.paymentMethod = paymentMethod;
        this.finalCost = finalCost;
    }

    public String getFullName() { return fullName; }
    public String getNid() { return nid; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDate getPickupDate() { return pickupDate; }
    public String getCarID() { return carID; }
    public String getCarName() { return carName; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getFinalCost() { return finalCost; }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setStatus(String status) {
        this.status = status;


    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }



    public void setExpectedDelivery(LocalDate expectedDelivery) {
        ExpectedDelivery = expectedDelivery;
    }
}
