package com.example.haqs_bay_automobili.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable {
    private String paymentId;
    private String invoiceId;
    private String customerId;
    private double amountPaid;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String receiptRef;

    public Payment(String paymentId, String invoiceId, String customerId, double amountPaid, String paymentMethod, LocalDate paymentDate, String receiptRef) {
        this.paymentId = paymentId;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.receiptRef = receiptRef;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getReceiptRef() { return receiptRef; }
    public void setReceiptRef(String receiptRef) { this.receiptRef = receiptRef; }

    @Override
    public String toString() {
        return paymentId + " - Inv: " + invoiceId + " ($" + amountPaid + ")";
    }
}
