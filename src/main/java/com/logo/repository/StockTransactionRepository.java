package com.logo.repository;

import com.logo.model.RealWorldService;
import com.logo.model.StockTransaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StockTransactionRepository {
    private static final List<StockTransaction> transactionList = new ArrayList<>();
    private static int nextId = 0;

    public StockTransaction save(StockTransaction transaction) {
        transaction.setId(nextId);
        nextId += 1;
        transactionList.add(transaction);
        return transaction;
    }

    public Optional<StockTransaction> findById(int id) {
        return transactionList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public List<StockTransaction> getAll() {
        return transactionList;
    }

    public void delete(StockTransaction transaction) {
        transactionList.remove(transaction);
    }

    public Optional<StockTransaction> findByDocumentNumber(String code) {
        return transactionList.stream().filter(it -> it.getDocumentNumber().equals(code)).findFirst();
    }
    //Client only need to send Transaction Id to refer to existing transaction.
    //We replace transaction list that came from user with our existing transaction entities.
    public List<StockTransaction> fetchTransactionsFromIds(List<StockTransaction> emptyTransactions) {
        return emptyTransactions.stream()
                .map(it -> this.findById(it.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}

