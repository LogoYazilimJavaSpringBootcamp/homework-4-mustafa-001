package com.logo.model;

import com.logo.model.enums.StockTransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Models a warehouse transaction in isbasi app.
//İşbaşı uygulamasınının Stok ve Hizmet kısmındaki Stok Hareketleri kısmını modeller.
public class StockTransaction {
    private int id;
    private String documentNumber;
    private StockTransactionType type;
    private LocalDate date;
    private String description;
    private List<ProductOrServiceAmountPair> products = new ArrayList<>();

    public List<ProductOrServiceAmountPair> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrServiceAmountPair> products) {
        this.products = products;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
