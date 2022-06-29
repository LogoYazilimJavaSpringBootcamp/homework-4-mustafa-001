package com.logo.model;

//Entity to use when storing a Product and its amount in Invoice or StockTransaction.
public record ProductOrServiceAmountPair(Product product, Double amount) {

}
