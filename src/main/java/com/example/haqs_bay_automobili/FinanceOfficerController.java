package com.example.haqs_bay_automobili;

import com.example.haqs_bay_automobili.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FinanceOfficerController implements Initializable {

    private DataManager dataManager;

    // --- Goal 1: Manage Customer Payments ---
    @FXML private ComboBox<SaleTransaction> comboPaymentInvoice;
    @FXML private TextField txtPaymentAmount;
    @FXML private ComboBox<String> comboPaymentMethod;
    @FXML private TableView<Payment> tblPayments;
    @FXML private TableColumn<Payment, String> colPayId, colPayInv, colPayCust, colPayMethod;
    @FXML private TableColumn<Payment, Number> colPayAmount;
    @FXML private Label lblPaymentStatus;

    // --- Goal 2: Track Import Costs ---
    @FXML private ComboBox<Car> comboImportCar;
    @FXML private TextField txtLCNumber, txtCustomsDuty, txtFreightFee, txtPortHandlingFee;
    @FXML private TableView<ImportCost> tblImportCosts;
    @FXML private TableColumn<ImportCost, String> colImpId, colImpChassis, colImpLC;
    @FXML private TableColumn<ImportCost, Number> colImpDuty, colImpFreight, colImpTotal;
    @FXML private Label lblImportCostStatus;

    // --- Goal 3: Manage Showroom Expenses ---
    @FXML private ComboBox<String> comboExpenseCategory;
    @FXML private TextField txtExpenseDesc, txtExpenseAmount;
    @FXML private TableView<Expense> tblExpenses;
    @FXML private TableColumn<Expense, String> colExpId, colExpCategory, colExpDesc;
    @FXML private TableColumn<Expense, Number> colExpAmount;
    @FXML private Label lblExpenseStatus;

    // --- Goal 4: Calculate Profit ---
    @FXML private DatePicker dpProfitStart, dpProfitEnd;
    @FXML private Label lblTotalRevenue, lblTotalCosts, lblTotalExpenses, lblNetProfit;
    @FXML private TextArea txtProfitBreakdown;

    // --- Goal 5: Manage Outstanding Payments ---
    @FXML private TableView<Customer> tblOutstandingDues;
    @FXML private TableColumn<Customer, String> colDueCustId, colDueName, colDuePhone;
    @FXML private TableColumn<Customer, Number> colDueBalance;
    @FXML private TextField txtRemindCustId;
    @FXML private Label lblOutstandingStatus;

    // --- Goal 6: Generate Financial Reports ---
    @FXML private ComboBox<String> comboFinReportType;
    @FXML private DatePicker dpFinReportStart, dpFinReportEnd;
    @FXML private TextArea txtFinReportOutput;

    // --- Goal 7: Manage Financial Records ---
    @FXML private ComboBox<String> comboRecordType;
    @FXML private TextField txtRecCategory, txtRecDesc, txtRecAmount, txtRecReference;
    @FXML private TableView<FinancialRecord> tblFinancialLedger;
    @FXML private TableColumn<FinancialRecord, String> colRecId, colRecType, colRecCategory, colRecRef;
    @FXML private TableColumn<FinancialRecord, Number> colRecAmount;
    @FXML private Label lblLedgerStatus;

    // --- Goal 8: Monitor Cash Flow ---
    @FXML private DatePicker dpCashFlowStart, dpCashFlowEnd;
    @FXML private Label lblCashInflow, lblCashOutflow, lblNetCashPosition;
    @FXML private TextArea txtCashFlowAnalysis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        // 1. Payments Setup
        comboPaymentInvoice.setItems(dataManager.getSales());
        comboPaymentMethod.setItems(FXCollections.observableArrayList("Cash", "Bank Transfer", "Cheque", "Credit Card"));
        comboPaymentMethod.setValue("Bank Transfer");
        colPayId.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPaymentId()));
        colPayInv.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getInvoiceId()));
        colPayCust.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCustomerId()));
        colPayMethod.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPaymentMethod()));
        colPayAmount.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getAmountPaid()));
        tblPayments.setItems(dataManager.getPayments());

        // 2. Import Costs Setup
        comboImportCar.setItems(dataManager.getCars());
        colImpId.setCellValueFactory(i -> new SimpleStringProperty(i.getValue().getCostId()));
        colImpChassis.setCellValueFactory(i -> new SimpleStringProperty(i.getValue().getChassisNumber()));
        colImpLC.setCellValueFactory(i -> new SimpleStringProperty(i.getValue().getLcNumber()));
        colImpDuty.setCellValueFactory(i -> new SimpleDoubleProperty(i.getValue().getCustomsDuty()));
        colImpFreight.setCellValueFactory(i -> new SimpleDoubleProperty(i.getValue().getFreightFee()));
        colImpTotal.setCellValueFactory(i -> new SimpleDoubleProperty(i.getValue().getTotalImportCost()));
        tblImportCosts.setItems(dataManager.getImportCosts());

        // 3. Showroom Expenses Setup
        comboExpenseCategory.setItems(FXCollections.observableArrayList("Rent", "Utilities", "Salaries", "Maintenance", "Marketing"));
        comboExpenseCategory.setValue("Utilities");
        colExpId.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getExpenseId()));
        colExpCategory.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory()));
        colExpDesc.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDescription()));
        colExpAmount.setCellValueFactory(e -> new SimpleDoubleProperty(e.getValue().getAmount()));
        tblExpenses.setItems(dataManager.getExpenses());

        // 4. Profit Setup
        dpProfitStart.setValue(LocalDate.now().minusMonths(3));
        dpProfitEnd.setValue(LocalDate.now());

        // 5. Outstanding Dues Setup
        colDueCustId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCustomerId()));
        colDueName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        colDuePhone.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPhone()));
        colDueBalance.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getOutstandingBalance()));
        tblOutstandingDues.setItems(dataManager.getCustomers().filtered(c -> c.getOutstandingBalance() > 0));

        // 6. Financial Reports Setup
        comboFinReportType.setItems(FXCollections.observableArrayList("Income Statement", "Balance Sheet Summary", "Tax & Duty Schedule"));
        comboFinReportType.setValue("Income Statement");
        dpFinReportStart.setValue(LocalDate.now().minusMonths(1));
        dpFinReportEnd.setValue(LocalDate.now());

        // 7. Financial Ledger Setup
        comboRecordType.setItems(FXCollections.observableArrayList("Income", "Expense"));
        comboRecordType.setValue("Income");
        colRecId.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getRecordId()));
        colRecType.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getType()));
        colRecCategory.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getCategory()));
        colRecRef.setCellValueFactory(r -> new SimpleStringProperty(r.getValue().getReference()));
        colRecAmount.setCellValueFactory(r -> new SimpleDoubleProperty(r.getValue().getAmount()));
        tblFinancialLedger.setItems(dataManager.getFinancialRecords());

        // 8. Cash Flow Setup
        dpCashFlowStart.setValue(LocalDate.now().minusMonths(1));
        dpCashFlowEnd.setValue(LocalDate.now());
    }

    // --- Goal 1: Manage Customer Payments (Events 1-5) ---
    @FXML
    public void handleProcessPayment(ActionEvent event) {
        SaleTransaction sale = comboPaymentInvoice.getValue();
        String amountStr = txtPaymentAmount.getText().trim();
        String method = comboPaymentMethod.getValue();

        if (sale == null || amountStr.isEmpty()) {
            lblPaymentStatus.setText("Validation Failed: Select Invoice and enter Amount.");
            return;
        }

        try {
            double amountPaid = Double.parseDouble(amountStr);
            if (amountPaid <= 0) {
                lblPaymentStatus.setText("Validation Failed: Payment must be > 0.");
                return;
            }

            if (amountPaid > sale.getDueAmount()) {
                lblPaymentStatus.setText(String.format("Validation Warning: Amount ($%.2f) exceeds due ($%.2f)", amountPaid, sale.getDueAmount()));
            }

            String payId = "PAY-" + (800 + dataManager.getPayments().size() + 1);
            String recRef = "REC-" + (7000 + dataManager.getPayments().size() + 1);

            Payment payment = new Payment(payId, sale.getInvoiceId(), sale.getCustomerId(), amountPaid, method, LocalDate.now(), recRef);
            dataManager.getPayments().add(payment);

            // Update Sale & Customer Dues
            sale.setAmountPaid(sale.getAmountPaid() + amountPaid);
            Customer cust = dataManager.findCustomerById(sale.getCustomerId());
            if (cust != null) {
                cust.setOutstandingBalance(Math.max(0, cust.getOutstandingBalance() - amountPaid));
            }

            // Record to Financial Ledger
            dataManager.getFinancialRecords().add(new FinancialRecord("REC-" + (100 + dataManager.getFinancialRecords().size() + 1), "Income", "Customer Payment", "Payment for " + sale.getInvoiceId(), amountPaid, LocalDate.now(), payId));

            tblPayments.refresh();
            tblOutstandingDues.refresh();
            lblPaymentStatus.setText("Receipt Issued: " + recRef + " | Receipt Amount: $" + amountPaid);
            txtPaymentAmount.clear();
        } catch (NumberFormatException e) {
            lblPaymentStatus.setText("Invalid numeric payment amount.");
        }
    }

    // --- Goal 2: Track Import Costs (Events 1-5) ---
    @FXML
    public void handleAddImportCost(ActionEvent event) {
        Car car = comboImportCar.getValue();
        String lc = txtLCNumber.getText().trim();
        String dutyStr = txtCustomsDuty.getText().trim();
        String freightStr = txtFreightFee.getText().trim();
        String portStr = txtPortHandlingFee.getText().trim();

        if (car == null || lc.isEmpty() || dutyStr.isEmpty() || freightStr.isEmpty() || portStr.isEmpty()) {
            lblImportCostStatus.setText("Validation Error: All import fields required.");
            return;
        }

        try {
            double duty = Double.parseDouble(dutyStr);
            double freight = Double.parseDouble(freightStr);
            double port = Double.parseDouble(portStr);
            String costId = "IMP-" + (300 + dataManager.getImportCosts().size() + 1);

            ImportCost cost = new ImportCost(costId, car.getChassisNumber(), lc, duty, freight, port, LocalDate.now());
            dataManager.getImportCosts().add(cost);

            car.setImportCost(car.getImportCost() + cost.getTotalImportCost());

            dataManager.getFinancialRecords().add(new FinancialRecord("REC-" + (100 + dataManager.getFinancialRecords().size() + 1), "Expense", "Import Costs", "Customs & Freight for " + car.getChassisNumber(), cost.getTotalImportCost(), LocalDate.now(), costId));

            lblImportCostStatus.setText("Success: Import Cost logged for " + car.getChassisNumber() + " -> Total: $" + cost.getTotalImportCost());
            txtLCNumber.clear(); txtCustomsDuty.clear(); txtFreightFee.clear(); txtPortHandlingFee.clear();
        } catch (NumberFormatException e) {
            lblImportCostStatus.setText("Validation Error: Financial fields must be numeric.");
        }
    }

    // --- Goal 3: Manage Showroom Expenses (Events 1-5) ---
    @FXML
    public void handleAddExpense(ActionEvent event) {
        String cat = comboExpenseCategory.getValue();
        String desc = txtExpenseDesc.getText().trim();
        String amountStr = txtExpenseAmount.getText().trim();

        if (cat == null || desc.isEmpty() || amountStr.isEmpty()) {
            lblExpenseStatus.setText("Validation Error: Category, Description, and Amount required.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            String expId = "EXP-" + (400 + dataManager.getExpenses().size() + 1);

            Expense exp = new Expense(expId, cat, desc, amount, LocalDate.now());
            dataManager.getExpenses().add(exp);

            dataManager.getFinancialRecords().add(new FinancialRecord("REC-" + (100 + dataManager.getFinancialRecords().size() + 1), "Expense", cat, desc, amount, LocalDate.now(), expId));

            lblExpenseStatus.setText("Expense Logged: " + expId + " | Category: " + cat + " | Amount: $" + amount);
            txtExpenseDesc.clear(); txtExpenseAmount.clear();
        } catch (NumberFormatException e) {
            lblExpenseStatus.setText("Validation Error: Amount must be numeric.");
        }
    }

    // --- Goal 4: Calculate Profit (Events 1-5) ---
    @FXML
    public void handleCalculateProfit(ActionEvent event) {
        double totalRev = dataManager.getSales().stream().mapToDouble(SaleTransaction::getSaleAmount).sum();
        double totalImportCost = dataManager.getImportCosts().stream().mapToDouble(ImportCost::getTotalImportCost).sum();
        double totalExp = dataManager.getExpenses().stream().mapToDouble(Expense::getAmount).sum();

        double netProfit = totalRev - (totalImportCost + totalExp);

        lblTotalRevenue.setText(String.format("$%.2f", totalRev));
        lblTotalCosts.setText(String.format("$%.2f", totalImportCost));
        lblTotalExpenses.setText(String.format("$%.2f", totalExp));
        lblNetProfit.setText(String.format("$%.2f", netProfit));

        StringBuilder sb = new StringBuilder();
        sb.append("PROFIT & LOSS STATEMENT SUMMARY\n");
        sb.append("------------------------------------------\n");
        sb.append(String.format("Gross Sales Revenue  :  $%.2f\n", totalRev));
        sb.append(String.format("Less: Import & Customs: -$%.2f\n", totalImportCost));
        sb.append(String.format("Less: Showroom Expenses: -$%.2f\n", totalExp));
        sb.append("------------------------------------------\n");
        sb.append(String.format("NET PROFIT           :  $%.2f\n", netProfit));
        txtProfitBreakdown.setText(sb.toString());
    }

    // --- Goal 5: Manage Outstanding Payments (Events 1-5) ---
    @FXML
    public void handleSendPaymentReminder(ActionEvent event) {
        String custId = txtRemindCustId.getText().trim();
        if (custId.isEmpty()) {
            lblOutstandingStatus.setText("Enter Customer ID to send reminder.");
            return;
        }

        Customer cust = dataManager.findCustomerById(custId);
        if (cust == null) {
            lblOutstandingStatus.setText("Customer not found.");
            return;
        }

        if (cust.getOutstandingBalance() <= 0) {
            lblOutstandingStatus.setText("Customer " + custId + " has no outstanding balance.");
            return;
        }

        lblOutstandingStatus.setText(String.format("Payment Reminder & Notice issued to %s (%s) for balance of $%.2f", cust.getName(), cust.getPhone(), cust.getOutstandingBalance()));
        txtRemindCustId.clear();
    }

    // --- Goal 6: Generate Financial Reports (Events 1-5) ---
    @FXML
    public void handleGenerateFinancialReport(ActionEvent event) {
        String reportType = comboFinReportType.getValue();
        LocalDate start = dpFinReportStart.getValue();
        LocalDate end = dpFinReportEnd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("=====================================================\n");
        sb.append("         HAQ'S BAY AUTOMOBILI - ").append(reportType.toUpperCase()).append("\n");
        sb.append("         Period: ").append(start).append(" to ").append(end).append("\n");
        sb.append("=====================================================\n\n");

        if (reportType.equalsIgnoreCase("Income Statement")) {
            double rev = dataManager.getSales().stream().mapToDouble(SaleTransaction::getSaleAmount).sum();
            double exp = dataManager.getExpenses().stream().mapToDouble(Expense::getAmount).sum();
            sb.append(String.format("Total Revenue Received : $%.2f\n", rev));
            sb.append(String.format("Total Operating Expenses: $%.2f\n", exp));
            sb.append(String.format("Operating Profit       : $%.2f\n", (rev - exp)));
        } else if (reportType.equalsIgnoreCase("Balance Sheet Summary")) {
            double assets = dataManager.getCars().stream().mapToDouble(Car::getSellingPrice).sum();
            double receivables = dataManager.getCustomers().stream().mapToDouble(Customer::getOutstandingBalance).sum();
            sb.append(String.format("Showroom Inventory Assets: $%.2f\n", assets));
            sb.append(String.format("Accounts Receivable      : $%.2f\n", receivables));
            sb.append(String.format("Total Liquid Capital     : $%.2f\n", (assets + receivables)));
        } else {
            for (ImportCost i : dataManager.getImportCosts()) {
                sb.append(String.format("• Chassis: %s | LC: %s | Duty: $%.2f | Total Cost: $%.2f\n", i.getChassisNumber(), i.getLcNumber(), i.getCustomsDuty(), i.getTotalImportCost()));
            }
        }

        txtFinReportOutput.setText(sb.toString());
    }

    // --- Goal 7: Manage Financial Records (Events 1-5) ---
    @FXML
    public void handleAddFinancialRecord(ActionEvent event) {
        String type = comboRecordType.getValue();
        String cat = txtRecCategory.getText().trim();
        String desc = txtRecDesc.getText().trim();
        String amountStr = txtRecAmount.getText().trim();
        String ref = txtRecReference.getText().trim();

        if (cat.isEmpty() || amountStr.isEmpty() || ref.isEmpty()) {
            lblLedgerStatus.setText("Validation Error: Category, Amount, and Reference required.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            String recId = "REC-" + (100 + dataManager.getFinancialRecords().size() + 1);

            FinancialRecord rec = new FinancialRecord(recId, type, cat, desc, amount, LocalDate.now(), ref);
            dataManager.getFinancialRecords().add(rec);

            lblLedgerStatus.setText("Success: Recorded in Ledger [" + recId + "] Ref: " + ref);
            txtRecCategory.clear(); txtRecDesc.clear(); txtRecAmount.clear(); txtRecReference.clear();
        } catch (NumberFormatException e) {
            lblLedgerStatus.setText("Validation Error: Amount must be numeric.");
        }
    }

    // --- Goal 8: Monitor Cash Flow (Events 1-5) ---
    @FXML
    public void handleAnalyzeCashFlow(ActionEvent event) {
        double inflow = dataManager.getPayments().stream().mapToDouble(Payment::getAmountPaid).sum();
        double outflow = dataManager.getExpenses().stream().mapToDouble(Expense::getAmount).sum() +
                         dataManager.getImportCosts().stream().mapToDouble(ImportCost::getTotalImportCost).sum();

        double netPosition = inflow - outflow;

        lblCashInflow.setText(String.format("$%.2f", inflow));
        lblCashOutflow.setText(String.format("$%.2f", outflow));
        lblNetCashPosition.setText(String.format("$%.2f", netPosition));

        StringBuilder sb = new StringBuilder();
        sb.append("CASH FLOW AUDIT ANALYSIS\n");
        sb.append("------------------------------------------\n");
        sb.append(String.format("Total Cash Inflow (Customer Collections) :  $%.2f\n", inflow));
        sb.append(String.format("Total Cash Outflow (Imports + Expenses)   : -$%.2f\n", outflow));
        sb.append("------------------------------------------\n");
        sb.append(String.format("NET CASH SURPLUS / DEFICIT              :  $%.2f\n", netPosition));
        txtCashFlowAnalysis.setText(sb.toString());
    }

    @FXML
    public void handleBackToRoleSelection(ActionEvent event) {
        MainApp.loadScene("role_selection.fxml", "Haq's Bay Automobili - Role Selection", 900, 600);
    }
}
