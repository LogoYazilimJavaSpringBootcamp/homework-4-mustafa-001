package com.logo.service;

import com.logo.model.ProductOrServiceAmountPair;
import com.logo.model.Invoice;
import com.logo.model.enums.InvoiceType;
import com.logo.repository.ProductOrServiceAmountPairRepository;
import com.logo.repository.ProductRepository;
import com.logo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrServiceAmountPairRepository productOrServiceAmountPairRepository;

    public Invoice create(Invoice request) {
        List<ProductOrServiceAmountPair> list = new ArrayList<>();
        for (ProductOrServiceAmountPair it : request.getProducts()) {
            ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
            productOrServiceAmountPairRepository.save(pair);
            list.add(pair);
        }
        request.setProducts(list);
        return invoiceRepository.save(request);
    }

    public List<Invoice> getAllTransactions() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceByDocumentNumber(String documentNumber) {
        return invoiceRepository.findByDocumentNumber(documentNumber);
    }

    public Optional<Invoice> getInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    public Invoice update(int id, Invoice invoice) {
        System.out.println("Updating invoice: " + id + "  to " + invoice.toString());
        var oldInvoiceOpt = invoiceRepository.findById(id);
        if (oldInvoiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldInvoice = oldInvoiceOpt.get();

        if (!invoice.getProducts().isEmpty()) {
            List<ProductOrServiceAmountPair> list = new ArrayList<>();
            for (ProductOrServiceAmountPair it : invoice.getProducts()) {
                ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
                productOrServiceAmountPairRepository.save(pair);
                list.add(pair);
            }
            productOrServiceAmountPairRepository.deleteAll(oldInvoice.getProducts());
            oldInvoice.setProducts(list);
        }
        if (invoice.getDocumentNumber() != null) oldInvoice.setDocumentNumber(invoice.getDocumentNumber());
        if (invoice.getInvoiceDate() != null) oldInvoice.setInvoiceDate(invoice.getInvoiceDate());
        if (invoice.getAddress() != null) oldInvoice.setAddress(invoice.getAddress());
        if (invoice.getCustomer() != null) oldInvoice.setCustomer(invoice.getCustomer());
        if (invoice.getCurrency() != null) oldInvoice.setCurrency(invoice.getCurrency());
        if (invoice.getDeliveryDate() != null) oldInvoice.setDeliveryDate(invoice.getDeliveryDate());
        if (invoice.getPaymentDate() != null) oldInvoice.setPaymentDate(invoice.getPaymentDate());
        if (invoice.getDiscountRate() != null) oldInvoice.setDiscountRate(invoice.getDiscountRate());
        if (invoice.getShipmentAdress().isPresent())
            oldInvoice.setShipmentAdress(invoice.getShipmentAdress());
        return oldInvoice;
    }

    public void delete(int id) {
        System.out.println("Deleting salesInvoice: " + id);
        var invoiceOpt = invoiceRepository.findById(id);
        if (invoiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        invoiceRepository.delete(invoiceOpt.get());
    }

    public List<Invoice> getInvoicesByType(InvoiceType type) {
        return invoiceRepository.findByInvoiceType(type);
    }
}
