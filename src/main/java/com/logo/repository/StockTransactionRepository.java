package com.logo.repository;

import com.logo.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    Optional<StockTransaction> findById(int id);

    Optional<StockTransaction> findByDocumentNumber(String code);
}

