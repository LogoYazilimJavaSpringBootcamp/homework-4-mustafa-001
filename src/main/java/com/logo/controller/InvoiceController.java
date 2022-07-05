package com.logo.controller;

import com.logo.model.Invoice;
import com.logo.model.enums.InvoiceType;
import com.logo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/type/{type}")
    public List<Invoice> getInvoicesByType(@PathVariable InvoiceType type) {
        return invoiceService.getInvoicesByType(type);
    }

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable int id) {
        return invoiceService.getInvoiceById(id).orElse(null);
    }

    @GetMapping("/documentNumber/{number}")
    public Invoice getByDocumentNumber(@PathVariable String number) {
        var transaction = invoiceService.getInvoiceByDocumentNumber(number);
        return transaction.orElse(null);
    }

    @PostMapping
    public Invoice add(@RequestBody Invoice invoice) {
        return invoiceService.create(invoice);
    }

    @PutMapping("/{id}")
    public Invoice update(@RequestBody Invoice invoice, @PathVariable int id) {
        return invoiceService.update(id, invoice);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        invoiceService.delete(id);
    }

}
