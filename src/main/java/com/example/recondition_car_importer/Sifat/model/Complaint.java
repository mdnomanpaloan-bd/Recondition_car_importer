package com.example.recondition_car_importer.Sifat.model;

import java.io.Serializable;

public class Complaint implements Serializable {
    private static final long serialVersionUID = 1L;

    private String complaintId;
    private String customerName;
    private String issue;
    private String priority; // High, Medium, Low
    private String status;   // Pending, In Progress, Resolved
    private String resolutionDetails;

    public Complaint(String complaintId, String customerName, String issue, String priority, String status, String resolutionDetails) {
        this.complaintId = complaintId;
        this.customerName = customerName;
        this.issue = issue;
        this.priority = priority;
        this.status = status;
        this.resolutionDetails = resolutionDetails;
    }

    public String getComplaintId() { return complaintId; }
    public void setComplaintId(String complaintId) { this.complaintId = complaintId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResolutionDetails() { return resolutionDetails; }
    public void setResolutionDetails(String resolutionDetails) { this.resolutionDetails = resolutionDetails; }
}
