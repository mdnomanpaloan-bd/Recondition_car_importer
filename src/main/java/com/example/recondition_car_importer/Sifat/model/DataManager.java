package com.example.recondition_car_importer.Sifat.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private final ObservableList<Car> cars = FXCollections.observableArrayList();
    private final ObservableList<TestDriveRequest> testDriveRequests = FXCollections.observableArrayList();
    private final ObservableList<SalesRep> salesReps = FXCollections.observableArrayList();
    private final ObservableList<Complaint> complaints = FXCollections.observableArrayList();
    private final ObservableList<Invoice> invoices = FXCollections.observableArrayList();
    private final ObservableList<Payment> payments = FXCollections.observableArrayList();
    private final ObservableList<CarLoan> loans = FXCollections.observableArrayList();
    private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private final ObservableList<EmployeeSalary> employeeSalaries = FXCollections.observableArrayList();

    private static final String DATA_DIR = "data";

    private DataManager() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        loadAllData();
        if (cars.isEmpty()) {
            initSeedData();
        }
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ObservableList<Car> getCars() { return cars; }
    public ObservableList<TestDriveRequest> getTestDriveRequests() { return testDriveRequests; }
    public ObservableList<SalesRep> getSalesReps() { return salesReps; }
    public ObservableList<Complaint> getComplaints() { return complaints; }
    public ObservableList<Invoice> getInvoices() { return invoices; }
    public ObservableList<Payment> getPayments() { return payments; }
    public ObservableList<CarLoan> getLoans() { return loans; }
    public ObservableList<Expense> getExpenses() { return expenses; }
    public ObservableList<EmployeeSalary> getEmployeeSalaries() { return employeeSalaries; }

    @SuppressWarnings("unchecked")
    public <T> void loadList(String filename, ObservableList<T> targetList) {
        File file = new File(DATA_DIR, filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<T> loaded = (List<T>) ois.readObject();
                targetList.setAll(loaded);
            } catch (Exception e) {
                System.err.println("Could not load " + filename + ": " + e.getMessage());
            }
        }
    }

    public <T> void saveList(String filename, List<T> sourceList) {
        File file = new File(DATA_DIR, filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(new ArrayList<>(sourceList));
        } catch (IOException e) {
            System.err.println("Could not save " + filename + ": " + e.getMessage());
        }
    }

    public void saveAllData() {
        saveList("inventory.bin", cars);
        saveList("test_drives.bin", testDriveRequests);
        saveList("sales_reps.bin", salesReps);
        saveList("complaints.bin", complaints);
        saveList("invoices.bin", invoices);
        saveList("payments.bin", payments);
        saveList("loans.bin", loans);
        saveList("expenses.bin", expenses);
        saveList("salaries.bin", employeeSalaries);
    }

    public void loadAllData() {
        loadList("inventory.bin", cars);
        loadList("test_drives.bin", testDriveRequests);
        loadList("sales_reps.bin", salesReps);
        loadList("complaints.bin", complaints);
        loadList("invoices.bin", invoices);
        loadList("payments.bin", payments);
        loadList("loans.bin", loans);
        loadList("expenses.bin", expenses);
        loadList("salaries.bin", employeeSalaries);
    }

    private void initSeedData() {
        // Cars
        Car c1 = new Car("CAR-101", "Toyota", "Harrier Premium", 2020, 4800000.0, "Available");
        Car c2 = new Car("CAR-102", "Honda", "Vezel Hybrid", 2019, 2750000.0, "Booked");
        Car c3 = new Car("CAR-103", "Nissan", "X-Trail Hybrid", 2021, 3600000.0, "Available");
        Car c4 = new Car("CAR-104", "Toyota", "Prado TX-L", 2022, 12500000.0, "Sold");
        c4.setAssignedRep("Rahim Ahmed");
        c4.setCustomerName("Tariq Hasan");
        c2.setAssignedRep("Karim Chowdhury");

        cars.addAll(c1, c2, c3, c4);

        // Sales Reps
        salesReps.addAll(
            new SalesRep("REP-01", "Rahim Ahmed", 5, 24500000.0),
            new SalesRep("REP-02", "Karim Chowdhury", 3, 11200000.0),
            new SalesRep("REP-03", "Nadia Islam", 4, 16800000.0)
        );

        // Test Drive Requests
        testDriveRequests.addAll(
            new TestDriveRequest("TR-01", "Tanvir Anjum", "Toyota Harrier Premium", LocalDate.now().minusDays(2), "Pending", ""),
            new TestDriveRequest("TR-02", "Sabrina Khan", "Nissan X-Trail Hybrid", LocalDate.now().minusDays(1), "Approved", "Customer requested 3PM slot.")
        );

        // Complaints
        complaints.addAll(
            new Complaint("CMP-01", "Mahmudur Rahman", "Minor scratch on rear bumper upon delivery", "Medium", "In Progress", "Sent to workshop for touch-up polish."),
            new Complaint("CMP-02", "Nusrat Jahan", "Delayed registration papers from BRTA", "High", "Pending", "")
        );

        // Invoices & Payments
        Invoice inv1 = new Invoice("INV-2026-01", "Tariq Hasan", "Toyota Prado TX-L", 12500000.0, 15.0, LocalDate.now().minusDays(10));
        inv1.setPaidAmount(5000000.0);
        invoices.add(inv1);

        payments.add(new Payment("PAY-01", "INV-2026-01", "Installment", 5000000.0, LocalDate.now().minusDays(10), 9375000.0));

        // Loans
        loans.add(new CarLoan("LN-501", "Tanvir Anjum", 3000000.0, 36, 9.5, "Pending"));

        // Expenses
        expenses.addAll(
            new Expense("EXP-01", "Rent", 250000.0, LocalDate.now().withDayOfMonth(1)),
            new Expense("EXP-02", "Utility", 35000.0, LocalDate.now().withDayOfMonth(5)),
            new Expense("EXP-03", "Maintenance", 45000.0, LocalDate.now().withDayOfMonth(8))
        );

        // Employee Salaries
        employeeSalaries.addAll(
            new EmployeeSalary("EMP-01", "Rahim Ahmed", 35000.0, 5.0, 5),
            new EmployeeSalary("EMP-02", "Karim Chowdhury", 30000.0, 5.0, 3)
        );

        saveAllData();
    }
}
