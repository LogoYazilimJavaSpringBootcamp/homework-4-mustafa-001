package com.logo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Models a Customer/Supplier in isbasi app.
//İşbaşı uygulamasındaki Müşteri/Tedarikçi kısımlarını modeller.
@Entity
@AllArgsConstructor
@Builder
 public  class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @OneToOne
    private Address address;
    @JsonBackReference
    @JoinColumn
    @ManyToOne
    private User user;
    private boolean isActive;
    @OneToMany(mappedBy = "id")
    @Schema(defaultValue = "[\"id\" : 10")
    private List<Invoice> invoiceList = new ArrayList<>();

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


    public void setInvoiceList(List<Invoice> invoiceList) {
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

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
