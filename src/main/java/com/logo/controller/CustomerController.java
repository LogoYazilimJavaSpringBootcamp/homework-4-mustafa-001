package com.logo.controller;

import com.logo.model.Customer;
import com.logo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/name")
    public Customer getByName(@RequestParam String name) {
        var customer = customerService.getCustomerByName(name);
        return customer.orElse(null);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        return customer.orElse(null);
    }

    @GetMapping("/active/{activeStatus}")
    public List<Customer> getByIsActive(@PathVariable boolean activeStatus) {
        return customerService.getByIsActive(activeStatus);
    }

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable long id) {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        customerService.delete(id);
    }
}
