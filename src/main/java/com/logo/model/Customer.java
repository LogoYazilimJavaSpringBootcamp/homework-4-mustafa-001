package com.logo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Models a Customer/Supplier in isbasi app.
//İşbaşı uygulamasındaki Müşteri/Tedarikçi kısımlarını modeller.
@Entity
 public  class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @OneToOne
    private Address address;
    @JoinColumn
    @ManyToOne
    private User user;
    private boolean isActive;
    @OneToMany(mappedBy = "id")
    private List<SalesInvoice> invoiceList = new ArrayList<>();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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


    public void setInvoiceList(List<SalesInvoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<SalesInvoice> getInvoiceList() {
        return invoiceList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
