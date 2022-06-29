package com.logo.model;

import java.util.ArrayList;
import java.util.List;

//Models a Customer/Supplier in isbasi app.
//İşbaşı uygulamasındaki Müşteri/Tedarikçi kısımlarını modeller.
public class Customer {

    private int id;
    private String name;
    private int age;
    private boolean isActive;
    private List<SalesInvoice> invoiceList = new ArrayList<>();

    public Customer(String name, int age, List<SalesInvoice> orderList) {
        super();
        this.name = name;
        this.age = age;
        this.invoiceList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<SalesInvoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<SalesInvoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
