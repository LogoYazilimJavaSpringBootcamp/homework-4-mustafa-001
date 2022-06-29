package com.logo.model;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Models a Customer/Supplier in isbasi app.
//İşbaşı uygulamasındaki Müşteri/Tedarikçi kısımlarını modeller.
@Entity
 public  class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private boolean isActive;
    @Transient
    private List<SalesInvoice> invoiceList = new ArrayList<>();

    public Customer() {

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
