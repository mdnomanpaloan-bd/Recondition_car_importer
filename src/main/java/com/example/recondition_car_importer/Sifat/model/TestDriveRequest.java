package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;
import java.time.LocalDate;

public class TestDriveRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String customerName;
    private String carModel;
    private LocalDate requestDate;
    private String status; // Pending, Approved, Rejected
    private String managerRemarks;

    public TestDriveRequest(String requestId, String customerName, String carModel, LocalDate requestDate, String status, String managerRemarks) {
        this.requestId = requestId;
        this.customerName = customerName;
        this.carModel = carModel;
        this.requestDate = requestDate;
        this.status = status;
        this.managerRemarks = managerRemarks;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getManagerRemarks() { return managerRemarks; }
    public void setManagerRemarks(String managerRemarks) { this.managerRemarks = managerRemarks; }
}
