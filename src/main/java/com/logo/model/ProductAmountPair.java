package com.logo.model;

import lombok.*;

import javax.persistence.*;

//Entity to use when storing a Product and its amount in Invoice or StockTransaction.
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductAmountPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Product productOrService;
    private  double amount;

    public ProductAmountPair() {
    }

    public ProductAmountPair(Product product1, double amount) {
        this.productOrService = product1;
        this.amount = amount;
    }
}
