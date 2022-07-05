package com.logo.repository.customerdao;

import com.logo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    public Optional<Customer> findByName(String name);

    public List<Customer> getByIsActive(boolean isActive);

    Customer save(Customer entity);

    Customer update(Customer entity);

//    Iterable<Customer> saveAll(Iterable<S> entities);

    Optional<Customer> findById(Long id);

//    boolean existsById(Long id);

    List<Customer> findAll();

//    Iterable<Customer> findAllById(Iterable<Long> ids);

//    long count();

//    void deleteById(Long id);

    void delete(Customer entity);

//    void deleteAllById(Iterable<Long> ids);
//
//    void deleteAll(Iterable<Customer> entities);
//
//    void deleteAll();
}
