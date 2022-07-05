package com.logo.repository.customerdao;

import com.logo.model.Customer;
import com.logo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class HibernateCustomerDao implements CustomerDao{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> getByIsActive(boolean isActive) {
        return customerRepository.getByIsActive(isActive);
    }

    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    //Hibernate already tracks changes and flueshes them at the end. So nothing is necessary.
    @Override
    public Customer update(Customer entity) {
        return entity;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return  customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }
}
