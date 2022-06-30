package com.logo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOrServiceAmountPairRepository extends JpaRepository<com.logo.model.ProductOrServiceAmountPair, Long> {
   Optional<com.logo.model.ProductOrServiceAmountPair> findById(long id);
}

