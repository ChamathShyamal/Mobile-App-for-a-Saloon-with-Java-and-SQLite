package com.example.salonsd;

public class FinishAppointments {

    private String adminsName;
    private String customersName;
    private String customersTelephone;
    private String transactionTime;
    private String transactionDate;
    private String transactionBillTotal;


    public FinishAppointments() {
        this.adminsName = "";
        this.customersName = "";
        this.customersTelephone = "";
        this.transactionTime = "";
        this.transactionDate = "";
        this.transactionBillTotal = "";
    }

    public String getAdminsName() {
        return adminsName;
    }

    public void setAdminsName(String adminsName) {
        this.adminsName = adminsName;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }

    public String getCustomersTelephone() {
        return customersTelephone;
    }

    public void setCustomersTelephone(String customersTelephone) {
        this.customersTelephone = customersTelephone;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBillTotal() {
        return transactionBillTotal;
    }

    public void setTransactionBillTotal(String transactionBillTotal) {
        this.transactionBillTotal = transactionBillTotal;
    }


    public FinishAppointments(String adminsName, String customersName, String customersTelephone, String transactionTime, String transactionDate, String transactionBillTotal) {
        this.adminsName = adminsName;
        this.customersName = customersName;
        this.customersTelephone = customersTelephone;
        this.transactionTime = transactionTime;
        this.transactionDate = transactionDate;
        this.transactionBillTotal = transactionBillTotal;
    }
}
