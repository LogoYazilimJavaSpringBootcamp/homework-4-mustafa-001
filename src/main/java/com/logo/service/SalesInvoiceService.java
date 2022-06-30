package com.logo.service;

import com.logo.model.ProductOrServiceAmountPair;
import com.logo.model.SalesInvoice;
import com.logo.repository.ProductOrServiceAmountPairRepository;
import com.logo.repository.ProductRepository;
import com.logo.repository.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesInvoiceService {
    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrServiceAmountPairRepository productOrServiceAmountPairRepository;

    public SalesInvoice create(SalesInvoice request) {
        List<ProductOrServiceAmountPair> list = new ArrayList<>();
        for (ProductOrServiceAmountPair it : request.getProducts()) {
            ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
            productOrServiceAmountPairRepository.save(pair);
            list.add(pair);
        }
        request.setProducts(list);
        return salesInvoiceRepository.save(request);
    }

    public List<SalesInvoice> getAllTransactions() {
        return salesInvoiceRepository.findAll();
    }

    public Optional<SalesInvoice> getSalesInvoiceByDocumentNumber(String documentNumber) {
        return salesInvoiceRepository.findByDocumentNumber(documentNumber);
    }

    public Optional<SalesInvoice> getSalesInvoiceById(int id) {
        return salesInvoiceRepository.findById(id);
    }

    public SalesInvoice update(int id, SalesInvoice salesInvoice) {
        System.out.println("Updating salesInvoice: " + id + "  to " + salesInvoice.toString());
        var oldInvoiceOpt = salesInvoiceRepository.findById(id);
        if (oldInvoiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldInvoice = oldInvoiceOpt.get();

        if (!salesInvoice.getProducts().isEmpty()) {
            List<ProductOrServiceAmountPair> list = new ArrayList<>();
            for (ProductOrServiceAmountPair it : salesInvoice.getProducts()) {
                ProductOrServiceAmountPair pair = new ProductOrServiceAmountPair(productRepository.findById(it.getProduct().getId()).get(), it.getAmount());
                productOrServiceAmountPairRepository.save(pair);
                list.add(pair);
            }
            productOrServiceAmountPairRepository.deleteAll(oldInvoice.getProducts());
            oldInvoice.setProducts(list);
        }
        if (salesInvoice.getDocumentNumber() != null) oldInvoice.setDocumentNumber(salesInvoice.getDocumentNumber());
        if (salesInvoice.getInvoiceDate() != null) oldInvoice.setInvoiceDate(salesInvoice.getInvoiceDate());
        if (salesInvoice.getAddress() != null) oldInvoice.setAddress(salesInvoice.getAddress());
        if (salesInvoice.getCustomer() != null) oldInvoice.setCustomer(salesInvoice.getCustomer());
        if (salesInvoice.getCurrency() != null) oldInvoice.setCurrency(salesInvoice.getCurrency());
        if (salesInvoice.getDeliveryDate() != null) oldInvoice.setDeliveryDate(salesInvoice.getDeliveryDate());
        if (salesInvoice.getPaymentDate() != null) oldInvoice.setPaymentDate(salesInvoice.getPaymentDate());
        if (salesInvoice.getDiscountRate() != null) oldInvoice.setDiscountRate(salesInvoice.getDiscountRate());
        if (salesInvoice.getShipmentAdress().isPresent())
            oldInvoice.setShipmentAdress(salesInvoice.getShipmentAdress());
        return oldInvoice;
    }

    public void delete(int id) {
        System.out.println("Deleting salesInvoice: " + id);
        var invoiceOpt = salesInvoiceRepository.findById(id);
        if (invoiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        salesInvoiceRepository.delete(invoiceOpt.get());
    }

}
