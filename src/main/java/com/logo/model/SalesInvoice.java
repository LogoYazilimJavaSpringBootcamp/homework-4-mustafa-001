package com.logo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//Models a Sales Invoice in isbasi app.
//İşbaşı uygulamasınının Satışlar kısmındaki Satış Faturası kısmını modeller.
//TODO Probably very similar to Sales Order, Purchase Invoice, Purchase Order, hence all 4 can be modeled
//a common abstract class and its specializations.
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class SalesInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Address address;
    @Transient
    private Optional<Address> shipmentAdress;
    private LocalDateTime invoiceDate;
    private LocalDateTime deliveryDate;
    private LocalDate paymentDate;
    private String documentNumber;
    private Currency currency;
    @OneToMany
    @ToString.Exclude
    private List<ProductOrServiceAmountPair> products = new ArrayList<>();
    private BigDecimal discountRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SalesInvoice that = (SalesInvoice) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

