package com.logo.service;

import com.logo.model.Product;
import com.logo.model.ProductAmountPair;
import com.logo.model.StockTransaction;
import com.logo.repository.ProductAmountPairRepository;
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
    private ProductAmountPairRepository productOrServiceAmountPairRepository;

    public StockTransaction create(StockTransaction request) {
        List<ProductAmountPair> list = new ArrayList<>();
        for (ProductAmountPair it : request.getProducts()) {
            ProductAmountPair pair = new ProductAmountPair(productRepository.findById(((Product)it.getProductOrService()).getId()).get(), it.getAmount());
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

    public Optional<StockTransaction> getStockTransactionById(long id) {
        return stockTransactionRepository.findById(id);
    }

    public StockTransaction update(long id, StockTransaction transaction) {
        System.out.println("Updating transaction: " + id + "  to " + transaction.toString());
        var oldTransactionOpt = stockTransactionRepository.findById(id);
        if (oldTransactionOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldTransaction = oldTransactionOpt.get();
        if (!transaction.getProducts().isEmpty()) {
            List<ProductAmountPair> list = new ArrayList<>();
            for (ProductAmountPair it : transaction.getProducts()) {
                ProductAmountPair pair = new ProductAmountPair(productRepository.findById(it.getProductOrService().getId()).get(), it.getAmount());
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
