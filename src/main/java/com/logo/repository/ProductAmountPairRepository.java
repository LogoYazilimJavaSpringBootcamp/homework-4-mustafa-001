package com.logo.repository;

import com.logo.model.ProductAmountPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductAmountPairRepository extends JpaRepository<ProductAmountPair, Long> {
   Optional<ProductAmountPair> findById(long id);
}

