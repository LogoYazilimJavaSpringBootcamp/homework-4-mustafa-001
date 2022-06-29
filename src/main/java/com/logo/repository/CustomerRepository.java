package com.logo.repository;

import com.logo.model.Customer;
import com.logo.model.Order;
import com.logo.model.Product;
import com.logo.model.SalesInvoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class CustomerRepository {
    private static final List<Customer> customers= new ArrayList<>();
    private static int nextId = 0;


    private List<Customer> prepareCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("bilisim.io", 25, new ArrayList<>()));
        return customers;
    }

    public Customer save(Customer customer){
        customer.setId(nextId);
        nextId += 1;
        customer.setActive(true);
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByName(String name){
        return customers.stream().filter(it -> it.getName().equals(name)).findFirst();
    }

    public List<Customer> getByIsActive(boolean activeStatus){
        return customers.stream().filter(it -> it.isActive() == activeStatus).toList();
    }

    public Optional<Customer> findById(int id){
        return customers.stream().filter(it -> it.getId() == id).findFirst();
    }

    public List<Customer> getAll() {
        return customers;
    }

    public void  delete(Customer customer){
        customers.remove(customer);
    }

    //Client only need to send Customer Id to refer to existing customer.
    //We replace customer list that came from user with our existing customer entities.
    public List<Customer> fetchCustomersFromIds(List<Customer> emptyInvoices) {
        return emptyInvoices.stream()
                .map(it -> this.findById(it.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
