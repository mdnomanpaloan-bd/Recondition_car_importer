package com.example.recondition_car_importer.Alvi.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class UploadDocumentRecord implements Serializable {

    private String documentType;
    private String documentNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private boolean uploaded;



    public UploadDocumentRecord(String documentType,
                          String documentNumber,
                          LocalDate issueDate,
                          LocalDate expiryDate,
                          boolean uploaded) {

        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.uploaded = uploaded;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isUploaded() {
        return uploaded;
    }


}
