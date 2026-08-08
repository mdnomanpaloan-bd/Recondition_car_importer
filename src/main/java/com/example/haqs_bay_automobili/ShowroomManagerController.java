package com.example.haqs_bay_automobili;

import com.example.haqs_bay_automobili.model.Car;
import com.example.haqs_bay_automobili.model.Customer;
import com.example.haqs_bay_automobili.model.DataManager;
import com.example.haqs_bay_automobili.model.Reservation;
import com.example.haqs_bay_automobili.model.SaleTransaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ShowroomManagerController implements Initializable {

    private DataManager dataManager;

    // --- Goal 1: Manage Car Inventory ---
    @FXML private TextField txtInvChassis, txtInvBrand, txtInvModel, txtInvYear, txtInvPurchasePrice, txtInvSellingPrice;
    @FXML private TableView<Car> tblInventory;
    @FXML private TableColumn<Car, String> colInvChassis, colInvBrand, colInvModel, colInvStatus;
    @FXML private TableColumn<Car, Number> colInvPrice;
    @FXML private Label lblInvStatus;

    // --- Goal 2: Manage Car Pricing ---
    @FXML private ComboBox<Car> comboPricingCar;
    @FXML private TextField txtNewPrice, txtDiscountPercent;
    @FXML private Label lblFinalCalculatedPrice, lblPricingStatus;
    @FXML private TableView<Car> tblPricing;
    @FXML private TableColumn<Car, String> colPriceChassis, colPriceModel;
    @FXML private TableColumn<Car, Number> colPriceCurrent;

    // --- Goal 3: Handle Customer Inquiries ---
    @FXML private TextField txtInquiryQuery;
    @FXML private ComboBox<String> comboInquiryFilter;
    @FXML private TableView<Car> tblInquiries;
    @FXML private TableColumn<Car, String> colInqChassis, colInqBrand, colInqModel, colInqStatus;
    @FXML private TableColumn<Car, Number> colInqPrice;
    @FXML private Label lblInquiryCount;

    // --- Goal 4: Manage Car Reservations ---
    @FXML private ComboBox<Car> comboResCar;
    @FXML private ComboBox<Customer> comboResCustomer;
    @FXML private TextField txtBookingAmount;
    @FXML private TableView<Reservation> tblReservations;
    @FXML private TableColumn<Reservation, String> colResId, colResChassis, colResCustomer, colResStatus;
    @FXML private TableColumn<Reservation, Number> colResAmount;
    @FXML private Label lblResStatus;

    // --- Goal 5: Process Car Sales ---
    @FXML private ComboBox<Car> comboSaleCar;
    @FXML private ComboBox<Customer> comboSaleCustomer;
    @FXML private TextField txtSalePaidAmount;
    @FXML private TableView<SaleTransaction> tblSales;
    @FXML private TableColumn<SaleTransaction, String> colSaleInv, colSaleChassis, colSaleCustomer, colSaleStatus;
    @FXML private TableColumn<SaleTransaction, Number> colSaleAmount;
    @FXML private Label lblSaleStatus;

    // --- Goal 6: Monitor Showroom Stock ---
    @FXML private Label lblTotalStock, lblAvailableStock, lblReservedStock, lblSoldStock;
    @FXML private ComboBox<String> comboStockCategoryFilter;
    @FXML private TableView<Car> tblStockSummary;
    @FXML private TableColumn<Car, String> colStockChassis, colStockBrandModel, colStockStatus;
    @FXML private TableColumn<Car, Number> colStockPrice;

    // --- Goal 7: Manage Customer Records ---
    @FXML private TextField txtCustId, txtCustName, txtCustPhone, txtCustEmail, txtCustAddress;
    @FXML private TableView<Customer> tblCustomers;
    @FXML private TableColumn<Customer, String> colCustId, colCustName, colCustPhone, colCustEmail;
    @FXML private Label lblCustStatus;

    // --- Goal 8: Generate Showroom Reports ---
    @FXML private ComboBox<String> comboReportType;
    @FXML private DatePicker dpReportStart, dpReportEnd;
    @FXML private TextArea txtShowroomReportOutput;

    private FilteredList<Car> filteredInquiries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        // 1. Inventory Setup
        colInvChassis.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getChassisNumber()));
        colInvBrand.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrand()));
        colInvModel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getModel()));
        colInvPrice.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSellingPrice()));
        colInvStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        tblInventory.setItems(dataManager.getCars());

        // 2. Pricing Setup
        comboPricingCar.setItems(dataManager.getCars());
        colPriceChassis.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getChassisNumber()));
        colPriceModel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrand() + " " + c.getValue().getModel()));
        colPriceCurrent.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSellingPrice()));
        tblPricing.setItems(dataManager.getCars());

        // 3. Inquiries Setup
        comboInquiryFilter.setItems(FXCollections.observableArrayList("All", "Available", "Reserved", "Sold"));
        comboInquiryFilter.setValue("All");
        filteredInquiries = new FilteredList<>(dataManager.getCars(), p -> true);
        colInqChassis.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getChassisNumber()));
        colInqBrand.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrand()));
        colInqModel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getModel()));
        colInqPrice.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSellingPrice()));
        colInqStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        tblInquiries.setItems(filteredInquiries);

        // 4. Reservations Setup
        comboResCar.setItems(dataManager.getCars());
        comboResCustomer.setItems(dataManager.getCustomers());
        colResId.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getReservationId()));
        colResChassis.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getChassisNumber()));
        colResCustomer.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getCustomerName()));
        colResAmount.setCellValueFactory(r -> new SimpleDoubleProperty(r.getValue().getBookingAmount()));
        colResStatus.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getStatus()));
        tblReservations.setItems(dataManager.getReservations());

        // 5. Sales Setup
        comboSaleCar.setItems(dataManager.getCars());
        comboSaleCustomer.setItems(dataManager.getCustomers());
        colSaleInv.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getInvoiceId()));
        colSaleChassis.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getChassisNumber()));
        colSaleCustomer.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getCustomerName()));
        colSaleAmount.setCellValueFactory(s -> new SimpleDoubleProperty(s.getValue().getSaleAmount()));
        colSaleStatus.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getPaymentStatus()));
        tblSales.setItems(dataManager.getSales());

        // 6. Stock Setup
        comboStockCategoryFilter.setItems(FXCollections.observableArrayList("ALL", "Toyota", "Honda", "Nissan", "Subaru"));
        comboStockCategoryFilter.setValue("ALL");
        colStockChassis.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getChassisNumber()));
        colStockBrandModel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBrand() + " " + c.getValue().getModel()));
        colStockStatus.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));
        colStockPrice.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSellingPrice()));
        tblStockSummary.setItems(dataManager.getCars());
        updateStockMetrics();

        // 7. Customers Setup
        colCustId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCustomerId()));
        colCustName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colCustPhone.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPhone()));
        colCustEmail.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        tblCustomers.setItems(dataManager.getCustomers());

        // 8. Reports Setup
        comboReportType.setItems(FXCollections.observableArrayList("Inventory Valuation", "Sales Performance", "Reservation Summary"));
        comboReportType.setValue("Inventory Valuation");
        dpReportStart.setValue(LocalDate.now().minusMonths(1));
        dpReportEnd.setValue(LocalDate.now());
    }

    // --- Goal 1: Manage Car Inventory (Events 1-5) ---
    @FXML
    public void handleAddInventoryCar(ActionEvent event) {
        String chassis = txtInvChassis.getText().trim();
        String brand = txtInvBrand.getText().trim();
        String model = txtInvModel.getText().trim();
        String yrStr = txtInvYear.getText().trim();
        String pPriceStr = txtInvPurchasePrice.getText().trim();
        String sPriceStr = txtInvSellingPrice.getText().trim();

        if (chassis.isEmpty() || brand.isEmpty() || model.isEmpty() || yrStr.isEmpty() || pPriceStr.isEmpty() || sPriceStr.isEmpty()) {
            lblInvStatus.setText("Validation Failed: All fields are required.");
            return;
        }

        if (dataManager.findCarByChassis(chassis) != null) {
            lblInvStatus.setText("Verification Failed: Chassis number already exists!");
            return;
        }

        try {
            int year = Integer.parseInt(yrStr);
            double purchasePrice = Double.parseDouble(pPriceStr);
            double sellingPrice = Double.parseDouble(sPriceStr);

            Car newCar = new Car(chassis, brand, model, year, purchasePrice, 0.0, sellingPrice, "Available");
            dataManager.getCars().add(newCar);

            lblInvStatus.setText("Success: Car " + chassis + " added to inventory!");
            clearInventoryFields();
            updateStockMetrics();
        } catch (NumberFormatException e) {
            lblInvStatus.setText("Validation Error: Year and Prices must be numeric.");
        }
    }

    private void clearInventoryFields() {
        txtInvChassis.clear(); txtInvBrand.clear(); txtInvModel.clear();
        txtInvYear.clear(); txtInvPurchasePrice.clear(); txtInvSellingPrice.clear();
    }

    // --- Goal 2: Manage Car Pricing (Events 1-5) ---
    @FXML
    public void handleCalculateSellingPrice(ActionEvent event) {
        Car car = comboPricingCar.getValue();
        if (car == null) {
            lblPricingStatus.setText("Select a vehicle first.");
            return;
        }

        try {
            double price = txtNewPrice.getText().trim().isEmpty() ? car.getSellingPrice() : Double.parseDouble(txtNewPrice.getText().trim());
            double discount = txtDiscountPercent.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtDiscountPercent.getText().trim());

            double finalPrice = price * (1.0 - (discount / 100.0));
            lblFinalCalculatedPrice.setText(String.format("$%.2f", finalPrice));
            lblPricingStatus.setText("Calculated final price after " + discount + "% discount.");
        } catch (NumberFormatException e) {
            lblPricingStatus.setText("Invalid numeric values for price/discount.");
        }
    }

    @FXML
    public void handleSaveCarPrice(ActionEvent event) {
        Car car = comboPricingCar.getValue();
        if (car == null) {
            lblPricingStatus.setText("Select a vehicle first.");
            return;
        }
        try {
            double price = txtNewPrice.getText().trim().isEmpty() ? car.getSellingPrice() : Double.parseDouble(txtNewPrice.getText().trim());
            double discount = txtDiscountPercent.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtDiscountPercent.getText().trim());
            double finalPrice = price * (1.0 - (discount / 100.0));

            car.setSellingPrice(finalPrice);
            tblPricing.refresh();
            tblInventory.refresh();
            lblPricingStatus.setText("Price updated for " + car.getChassisNumber() + " -> $" + finalPrice);
        } catch (NumberFormatException e) {
            lblPricingStatus.setText("Update failed: invalid numeric format.");
        }
    }

    // --- Goal 3: Handle Customer Inquiries (Events 1-5) ---
    @FXML
    public void handleSearchInquiries(ActionEvent event) {
        String query = txtInquiryQuery.getText().toLowerCase().trim();
        String statusFilter = comboInquiryFilter.getValue();

        filteredInquiries.setPredicate(car -> {
            boolean matchesQuery = query.isEmpty() ||
                    car.getChassisNumber().toLowerCase().contains(query) ||
                    car.getBrand().toLowerCase().contains(query) ||
                    car.getModel().toLowerCase().contains(query);

            boolean matchesStatus = statusFilter == null || statusFilter.equalsIgnoreCase("All") ||
                    car.getStatus().equalsIgnoreCase(statusFilter);

            return matchesQuery && matchesStatus;
        });

        lblInquiryCount.setText("Matching Vehicles Found: " + filteredInquiries.size());
    }

    // --- Goal 4: Manage Car Reservations (Events 1-5) ---
    @FXML
    public void handleCreateReservation(ActionEvent event) {
        Car selectedCar = comboResCar.getValue();
        Customer selectedCust = comboResCustomer.getValue();
        String amountStr = txtBookingAmount.getText().trim();

        if (selectedCar == null || selectedCust == null || amountStr.isEmpty()) {
            lblResStatus.setText("Validation Failed: Select Car, Customer, and enter amount.");
            return;
        }

        if (!selectedCar.getStatus().equalsIgnoreCase("Available")) {
            lblResStatus.setText("Verification Error: Vehicle " + selectedCar.getChassisNumber() + " is " + selectedCar.getStatus());
            return;
        }

        try {
            double bookingAmt = Double.parseDouble(amountStr);
            String resId = "RES-" + (500 + dataManager.getReservations().size() + 1);

            Reservation res = new Reservation(resId, selectedCar.getChassisNumber(), selectedCust.getCustomerId(), selectedCust.getName(), LocalDate.now(), bookingAmt, "Active");
            dataManager.getReservations().add(res);

            selectedCar.setStatus("Reserved");
            tblInventory.refresh();
            updateStockMetrics();

            lblResStatus.setText("Success: Created Reservation " + resId + " for " + selectedCust.getName());
            txtBookingAmount.clear();
        } catch (NumberFormatException e) {
            lblResStatus.setText("Validation Error: Booking amount must be a number.");
        }
    }

    // --- Goal 5: Process Car Sales (Events 1-5) ---
    @FXML
    public void handleProcessSale(ActionEvent event) {
        Car selectedCar = comboSaleCar.getValue();
        Customer selectedCust = comboSaleCustomer.getValue();
        String paidStr = txtSalePaidAmount.getText().trim();

        if (selectedCar == null || selectedCust == null || paidStr.isEmpty()) {
            lblSaleStatus.setText("Validation Failed: Select Car, Customer, and enter Paid Amount.");
            return;
        }

        if (selectedCar.getStatus().equalsIgnoreCase("Sold")) {
            lblSaleStatus.setText("Verification Error: Vehicle is already SOLD!");
            return;
        }

        try {
            double amountPaid = Double.parseDouble(paidStr);
            String invId = "INV-" + (900 + dataManager.getSales().size() + 1);

            SaleTransaction sale = new SaleTransaction(invId, selectedCar.getChassisNumber(), selectedCust.getCustomerId(), selectedCust.getName(), LocalDate.now(), selectedCar.getSellingPrice(), amountPaid);
            dataManager.getSales().add(sale);

            if (sale.getDueAmount() > 0) {
                selectedCust.setOutstandingBalance(selectedCust.getOutstandingBalance() + sale.getDueAmount());
            }

            selectedCar.setStatus("Sold");
            tblInventory.refresh();
            tblCustomers.refresh();
            updateStockMetrics();

            lblSaleStatus.setText("Success: Invoice " + invId + " issued! Due: $" + sale.getDueAmount());
            txtSalePaidAmount.clear();
        } catch (NumberFormatException e) {
            lblSaleStatus.setText("Validation Error: Paid amount must be numeric.");
        }
    }

    // --- Goal 6: Monitor Showroom Stock (Events 1-5) ---
    @FXML
    public void handleFilterStock(ActionEvent event) {
        String brandFilter = comboStockCategoryFilter.getValue();
        if (brandFilter == null || brandFilter.equalsIgnoreCase("ALL")) {
            tblStockSummary.setItems(dataManager.getCars());
        } else {
            tblStockSummary.setItems(dataManager.getCars().filtered(c -> c.getBrand().equalsIgnoreCase(brandFilter)));
        }
    }

    private void updateStockMetrics() {
        int total = dataManager.getCars().size();
        long available = dataManager.getCars().stream().filter(c -> c.getStatus().equalsIgnoreCase("Available")).count();
        long reserved = dataManager.getCars().stream().filter(c -> c.getStatus().equalsIgnoreCase("Reserved")).count();
        long sold = dataManager.getCars().stream().filter(c -> c.getStatus().equalsIgnoreCase("Sold")).count();

        if (lblTotalStock != null) lblTotalStock.setText(String.valueOf(total));
        if (lblAvailableStock != null) lblAvailableStock.setText(String.valueOf(available));
        if (lblReservedStock != null) lblReservedStock.setText(String.valueOf(reserved));
        if (lblSoldStock != null) lblSoldStock.setText(String.valueOf(sold));
    }

    // --- Goal 7: Manage Customer Records (Events 1-5) ---
    @FXML
    public void handleAddCustomer(ActionEvent event) {
        String id = txtCustId.getText().trim();
        String name = txtCustName.getText().trim();
        String phone = txtCustPhone.getText().trim();
        String email = txtCustEmail.getText().trim();
        String addr = txtCustAddress.getText().trim();

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            lblCustStatus.setText("Validation Error: ID, Name, and Phone are required.");
            return;
        }

        if (dataManager.findCustomerById(id) != null) {
            lblCustStatus.setText("Verification Failed: Customer ID already exists!");
            return;
        }

        Customer cust = new Customer(id, name, phone, email, addr, 0.0);
        dataManager.getCustomers().add(cust);
        lblCustStatus.setText("Customer " + id + " (" + name + ") saved successfully!");
        txtCustId.clear(); txtCustName.clear(); txtCustPhone.clear(); txtCustEmail.clear(); txtCustAddress.clear();
    }

    // --- Goal 8: Generate Showroom Reports (Events 1-5) ---
    @FXML
    public void handleGenerateShowroomReport(ActionEvent event) {
        String type = comboReportType.getValue();
        LocalDate start = dpReportStart.getValue();
        LocalDate end = dpReportEnd.getValue();

        if (start == null || end == null || start.isAfter(end)) {
            txtShowroomReportOutput.setText("Error: Invalid date range parameters selected.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=====================================================\n");
        sb.append("          HAQ'S BAY AUTOMOBILI - ").append(type.toUpperCase()).append("\n");
        sb.append("          Period: ").append(start).append(" to ").append(end).append("\n");
        sb.append("=====================================================\n\n");

        if (type.equalsIgnoreCase("Inventory Valuation")) {
            double totalValue = 0.0;
            for (Car c : dataManager.getCars()) {
                sb.append(String.format("• [%s] %s %s - Status: %s | Price: $%.2f\n", c.getChassisNumber(), c.getBrand(), c.getModel(), c.getStatus(), c.getSellingPrice()));
                totalValue += c.getSellingPrice();
            }
            sb.append("\nTotal Showroom Asset Value: $").append(String.format("%.2f", totalValue));
        } else if (type.equalsIgnoreCase("Sales Performance")) {
            double totalSalesAmt = 0.0;
            for (SaleTransaction s : dataManager.getSales()) {
                sb.append(String.format("• Inv: %s | Car: %s | Cust: %s | Sale: $%.2f | Status: %s\n", s.getInvoiceId(), s.getChassisNumber(), s.getCustomerName(), s.getSaleAmount(), s.getPaymentStatus()));
                totalSalesAmt += s.getSaleAmount();
            }
            sb.append("\nTotal Sales Revenue Generated: $").append(String.format("%.2f", totalSalesAmt));
        } else {
            for (Reservation r : dataManager.getReservations()) {
                sb.append(String.format("• Res: %s | Car: %s | Cust: %s | Deposit: $%.2f\n", r.getReservationId(), r.getChassisNumber(), r.getCustomerName(), r.getBookingAmount()));
            }
            sb.append("\nTotal Active Reservations: ").append(dataManager.getReservations().size());
        }

        txtShowroomReportOutput.setText(sb.toString());
    }

    @FXML
    public void handleBackToRoleSelection(ActionEvent event) {
        MainApp.loadScene("role_selection.fxml", "Haq's Bay Automobili - Role Selection", 900, 600);
    }
}
