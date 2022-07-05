package com.logo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.logo.model.enums.StockTransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Models a warehouse transaction in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Stok Hareketleri kısmını modeller.
@Entity
@Builder
@AllArgsConstructor
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JoinColumn
    @ManyToOne
    @JsonBackReference
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private User user;
    private String documentNumber;
    @Enumerated(EnumType.STRING)
    private StockTransactionType type;
    private LocalDate date;
    private String description;
    @OneToMany
    private List<ProductAmountPair> products = new ArrayList<>();

    public StockTransaction() {

    }

    public List<ProductAmountPair> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAmountPair> products) {
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public StockTransactionType getType() {
        return type;
    }

    public void setType(StockTransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
