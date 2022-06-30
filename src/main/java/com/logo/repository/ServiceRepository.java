package com.logo.repository;

import com.logo.model.RealWorldService;
import com.logo.model.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<RealWorldService, Integer> {
    Optional<RealWorldService> findById(Long id);

    Optional<RealWorldService> findByServiceCode(String code);

    @Query(value = "select * from service where name=?", nativeQuery = true)
    List<RealWorldService> getServicesStartingWith(String searchQuery);

}

