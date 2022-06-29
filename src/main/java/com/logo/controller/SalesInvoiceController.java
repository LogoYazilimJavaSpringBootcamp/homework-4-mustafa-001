package com.logo.controller;

import com.logo.model.SalesInvoice;
import com.logo.service.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceService salesInvoiceService;

    @GetMapping
    public List<SalesInvoice> getAllServices() {
        return salesInvoiceService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public SalesInvoice getById(@PathVariable int id) {
        return salesInvoiceService.getSalesInvoiceById(id).orElse(null);
    }

    @GetMapping("/documentNumber/{number}")
    public SalesInvoice getByDocumentNumber(@PathVariable String number) {
        var transaction = salesInvoiceService.getSalesInvoiceByDocumentNumber(number);
        return transaction.orElse(null);
    }

    @PostMapping
    public SalesInvoice add(@RequestBody SalesInvoice salesInvoice) {
        return salesInvoiceService.create(salesInvoice);
    }

    @PutMapping("/{id}")
    public SalesInvoice update(@RequestBody SalesInvoice salesInvoice, @PathVariable int id) {
        return salesInvoiceService.update(id, salesInvoice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        salesInvoiceService.delete(id);
    }

}
