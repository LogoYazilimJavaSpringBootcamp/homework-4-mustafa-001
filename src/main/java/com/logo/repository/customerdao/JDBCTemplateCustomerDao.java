package com.logo.repository.customerdao;

import com.logo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JDBCTemplateCustomerDao implements CustomerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Customer> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getByIsActive(boolean isActive) {
        return null;
    }

    @Override
    public Customer save(Customer entity) {
         jdbcTemplate.update("INSERT INTO customer(id, name, age, is_active) VALUES (?, ?, ?, ?)", 100, entity.getName(), entity.getAge(), entity.isActive());
         return  entity;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void delete(Customer entity) {

    }
}
