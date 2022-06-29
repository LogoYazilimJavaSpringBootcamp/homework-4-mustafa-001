package com.logo.repository;

import com.logo.model.Customer;
import com.logo.model.Order;
import com.logo.model.Product;
import com.logo.model.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {



    public Optional<Customer> findByName(String name);

    public List<Customer> getByIsActive(boolean isActive);
}
