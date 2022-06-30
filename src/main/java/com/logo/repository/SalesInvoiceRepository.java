package com.logo.repository;

import com.logo.model.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
    Optional<SalesInvoice> findById(long id);

    Optional<SalesInvoice> findByDocumentNumber(String documentNumber);
}

