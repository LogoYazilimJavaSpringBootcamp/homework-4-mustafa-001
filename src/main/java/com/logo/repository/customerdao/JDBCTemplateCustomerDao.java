package com.logo.repository.customerdao;

import com.logo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JDBCTemplateCustomerDao implements CustomerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Customer> findByName(String name) {
        Customer result = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE name = ?", new BeanPropertyRowMapper<>(Customer.class), name);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public List<Customer> getByIsActive(boolean isActive) {
        return jdbcTemplate.query("SELECT * FROM customer WHERE is_active = ?", new BeanPropertyRowMapper<>(Customer.class), isActive);
    }

    @Override
    public Customer save(Customer entity) {
        jdbcTemplate.update("INSERT INTO customer( name, age, address_id, is_active) VALUES ( ?, ?, ?, ?)",
                entity.getName(), entity.getAge(), entity.getAddress().getId(), entity.isActive());
        return entity;
    }

    @Override
    public Customer update(Customer entity) {
        jdbcTemplate.update("UPDATE  customer SET name = ?, age = ? , address_id = ?, is_active = ? WHERE id = ?",
                entity.getName(), entity.getAge(), entity.getAddress().getId(), entity.isActive(), entity.getId());
        return entity;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Customer result = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id = ?", new BeanPropertyRowMapper<>(Customer.class), id);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public List<Customer> findAll() {
       return  jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public void delete(Customer entity) {
        jdbcTemplate.update("DELETE FROM customer WHERE id = ?", entity.getId());
    }
}
