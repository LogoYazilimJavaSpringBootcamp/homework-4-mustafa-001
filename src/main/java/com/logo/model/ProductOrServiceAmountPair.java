package com.logo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Entity to use when storing a Product and its amount in Invoice or StockTransaction.
@Entity
@Getter
@Setter
public class ProductOrServiceAmountPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Product product;
    private  double amount;

    public ProductOrServiceAmountPair() {
    }
    public ProductOrServiceAmountPair(Product product, double amount) {

        this.product = product;
        this.amount = amount;
    }

}
