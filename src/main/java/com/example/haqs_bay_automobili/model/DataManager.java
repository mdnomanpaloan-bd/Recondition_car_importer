package com.example.haqs_bay_automobili.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class DataManager {
    private static DataManager instance;

    private final ObservableList<Car> cars = FXCollections.observableArrayList();
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private final ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    private final ObservableList<SaleTransaction> sales = FXCollections.observableArrayList();
    private final ObservableList<Payment> payments = FXCollections.observableArrayList();
    private final ObservableList<ImportCost> importCosts = FXCollections.observableArrayList();
    private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private final ObservableList<FinancialRecord> financialRecords = FXCollections.observableArrayList();

    private DataManager() {
        seedInitialData();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void seedInitialData() {
        // Cars
        cars.add(new Car("CHS-1001", "Toyota", "Harrier Premium", 2021, 28000.0, 4500.0, 39500.0, "Available"));
        cars.add(new Car("CHS-1002", "Honda", "Vezel Hybrid Z", 2022, 22000.0, 3800.0, 31000.0, "Reserved"));
        cars.add(new Car("CHS-1003", "Nissan", "X-Trail Mode Premier", 2020, 24000.0, 4100.0, 34500.0, "Sold"));
        cars.add(new Car("CHS-1004", "Toyota", "Land Cruiser Prado", 2022, 65000.0, 9500.0, 89000.0, "Available"));
        cars.add(new Car("CHS-1005", "Subaru", "Forester 2.0i EyeSight", 2021, 23500.0, 3900.0, 33000.0, "Available"));

        // Customers
        customers.add(new Customer("CUST-101", "Rafiqul Islam", "+8801711223344", "rafiq@gmail.com", "Gulshan-2, Dhaka", 0.0));
        customers.add(new Customer("CUST-102", "Nusrat Jahan", "+8801819988776", "nusrat@yahoo.com", "Banani, Dhaka", 5000.0));
        customers.add(new Customer("CUST-103", "Tariq Mahmood", "+8801912345678", "tariq@outlook.com", "Dhanmondi, Dhaka", 12000.0));

        // Reservations
        reservations.add(new Reservation("RES-501", "CHS-1002", "CUST-102", "Nusrat Jahan", LocalDate.now().minusDays(5), 2000.0, "Active"));

        // Sales
        sales.add(new SaleTransaction("INV-901", "CHS-1003", "CUST-101", "Rafiqul Islam", LocalDate.now().minusDays(10), 34500.0, 34500.0));
        sales.add(new SaleTransaction("INV-902", "CHS-1002", "CUST-102", "Nusrat Jahan", LocalDate.now().minusDays(2), 31000.0, 26000.0));

        // Payments
        payments.add(new Payment("PAY-801", "INV-901", "CUST-101", 34500.0, "Bank Transfer", LocalDate.now().minusDays(10), "REC-7001"));
        payments.add(new Payment("PAY-802", "INV-902", "CUST-102", 26000.0, "Cheque", LocalDate.now().minusDays(2), "REC-7002"));

        // Import Costs
        importCosts.add(new ImportCost("IMP-301", "CHS-1001", "LC-990112", 3000.0, 1000.0, 500.0, LocalDate.now().minusDays(30)));
        importCosts.add(new ImportCost("IMP-302", "CHS-1002", "LC-990113", 2500.0, 900.0, 400.0, LocalDate.now().minusDays(25)));
        importCosts.add(new ImportCost("IMP-303", "CHS-1003", "LC-990114", 2700.0, 950.0, 450.0, LocalDate.now().minusDays(20)));

        // Expenses
        expenses.add(new Expense("EXP-401", "Rent", "Showroom Floor Rent - Gulshan", 4500.0, LocalDate.now().minusDays(15)));
        expenses.add(new Expense("EXP-402", "Utilities", "Electricity & Water Bills", 850.0, LocalDate.now().minusDays(12)));
        expenses.add(new Expense("EXP-403", "Salaries", "Sales Staff Payroll", 6000.0, LocalDate.now().minusDays(5)));

        // Financial Records
        financialRecords.add(new FinancialRecord("REC-101", "Income", "Car Sale", "Invoice INV-901 Sale", 34500.0, LocalDate.now().minusDays(10), "INV-901"));
        financialRecords.add(new FinancialRecord("REC-102", "Expense", "Import Duty", "LC Duty Payment CHS-1003", 4100.0, LocalDate.now().minusDays(20), "IMP-303"));
        financialRecords.add(new FinancialRecord("REC-103", "Expense", "Showroom Rent", "Monthly Floor Rent", 4500.0, LocalDate.now().minusDays(15), "EXP-401"));
    }

    public ObservableList<Car> getCars() { return cars; }
    public ObservableList<Customer> getCustomers() { return customers; }
    public ObservableList<Reservation> getReservations() { return reservations; }
    public ObservableList<SaleTransaction> getSales() { return sales; }
    public ObservableList<Payment> getPayments() { return payments; }
    public ObservableList<ImportCost> getImportCosts() { return importCosts; }
    public ObservableList<Expense> getExpenses() { return expenses; }
    public ObservableList<FinancialRecord> getFinancialRecords() { return financialRecords; }

    public Car findCarByChassis(String chassis) {
        for (Car c : cars) {
            if (c.getChassisNumber().equalsIgnoreCase(chassis)) return c;
        }
        return null;
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    public SaleTransaction findSaleByInvoice(String invoiceId) {
        for (SaleTransaction s : sales) {
            if (s.getInvoiceId().equalsIgnoreCase(invoiceId)) return s;
        }
        return null;
    }
}
