package com.example.haqs_bay_automobili.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private double outstandingBalance;

    public Customer(String customerId, String name, String phone, String email, String address, double outstandingBalance) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.outstandingBalance = outstandingBalance;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getOutstandingBalance() { return outstandingBalance; }
    public void setOutstandingBalance(double outstandingBalance) { this.outstandingBalance = outstandingBalance; }

    @Override
    public String toString() {
        return customerId + " - " + name + " (" + phone + ")";
    }
}
