package com.logo.service;

import com.logo.model.ProductOrServiceAmountPair;
import com.logo.model.StockTransaction;
import com.logo.repository.ProductOrServiceAmountPairRepository;
import com.logo.repository.ProductRepository;
import com.logo.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockTransactionService {
    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrServiceAmountPairRepository productOrServiceAmountPairRepository;

    public StockTransaction create(StockTransaction request) {
        List<ProductOrServiceAmountPair> list = new ArrayList<>();
        for (ProductOrServiceAmountPair it : request.getProducts()) {
            ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
            productOrServiceAmountPairRepository.save(pair);
            list.add(pair);
        }
        request.setProducts(list);
        return stockTransactionRepository.save(request);
    }

    public List<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.findAll();
    }

    public Optional<StockTransaction> getStockTransactionByDocumentNumber(String documentNumber) {
        return stockTransactionRepository.findByDocumentNumber(documentNumber);
    }

    public Optional<StockTransaction> getStockTransactionById(int id) {
        return stockTransactionRepository.findById(id);
    }

    public StockTransaction update(int id, StockTransaction transaction) {
        System.out.println("Updating transaction: " + id + "  to " + transaction.toString());
        var oldTransactionOpt = stockTransactionRepository.findById(id);
        if (oldTransactionOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldTransaction = oldTransactionOpt.get();
        if (!transaction.getProducts().isEmpty()) {
            List<ProductOrServiceAmountPair> list = new ArrayList<>();
            for (ProductOrServiceAmountPair it : transaction.getProducts()) {
                ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
                productOrServiceAmountPairRepository.save(pair);
                list.add(pair);
            }
            productOrServiceAmountPairRepository.deleteAll(oldTransaction.getProducts());
            oldTransaction.setProducts(list);
        }
        if (transaction.getDate() != null) oldTransaction.setDate(transaction.getDate());
        if (transaction.getDocumentNumber() != null) oldTransaction.setDocumentNumber(transaction.getDocumentNumber());
        if (transaction.getDescription() != null) oldTransaction.setDescription(transaction.getDescription());
        if (!transaction.getProducts().isEmpty()) oldTransaction.setProducts(transaction.getProducts());
        if (transaction.getType() != null) oldTransaction.setType(transaction.getType());
        return oldTransaction;
    }

    public void delete(int id) {
        System.out.println("Deleting transaction: " + id);
        var serviceOpt = stockTransactionRepository.findById(id);
        if (serviceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        stockTransactionRepository.delete(serviceOpt.get());
    }

}
