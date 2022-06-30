package com.logo.repository;

import com.logo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   Optional<Product> findById(long id);

    Optional<Product> findByBarcode(String code);

    @Query(value = "select * from product where name=?", nativeQuery = true)
    List<Product> getProductsStartingWith(String searchQuery);
}

