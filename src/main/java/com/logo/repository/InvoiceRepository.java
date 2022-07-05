package com.logo.repository;

import com.logo.model.Invoice;
import com.logo.model.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findById(long id);

    Optional<Invoice> findByDocumentNumber(String documentNumber);

    List<Invoice> findByInvoiceType(InvoiceType type);
}

