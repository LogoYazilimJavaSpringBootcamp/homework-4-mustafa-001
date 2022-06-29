package com.logo.controller;

import com.logo.model.StockTransaction;
import com.logo.service.StockTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class StockTransactionController {

    @Autowired
    private StockTransactionService stockTransactionService;

    @GetMapping
    public List<StockTransaction> getAllServices() {
        return stockTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public StockTransaction getById(@PathVariable int id) {
        return stockTransactionService.getStockTransactionById(id).orElse(null);
    }

    @GetMapping("/documentNumber/{number}")
    public StockTransaction getByDocumentNumber(@PathVariable String number) {
        var transaction = stockTransactionService.getStockTransactionByDocumentNumber(number);
        return transaction.orElse(null);
    }

    @PostMapping
    public StockTransaction add(@RequestBody StockTransaction transaction) {
        return stockTransactionService.create(transaction);
    }

    @PutMapping("/{id}")
    public StockTransaction update(@RequestBody StockTransaction transaction, @PathVariable int id) {
        return stockTransactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        stockTransactionService.delete(id);
    }

}
