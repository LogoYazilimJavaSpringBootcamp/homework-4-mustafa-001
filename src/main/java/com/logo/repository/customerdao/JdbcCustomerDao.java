package com.logo.repository.customerdao;

import com.logo.model.Customer;
import com.logo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCustomerDao implements CustomerDao {
    @Autowired
    AddressRepository addressRepository;
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/isbasi",
                    "postgres",
                    "pgpassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
        long generatedKey;
        try {
            Statement statement = connection.createStatement();
            var preparedConnection = connection.prepareStatement("INSERT INTO customer (name, age, is_active, address_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedConnection.setString(1, entity.getName());
            preparedConnection.setInt(2, entity.getAge());
            preparedConnection.setBoolean(3, entity.isActive());
            preparedConnection.setLong(4, entity.getAddress().getId());
            preparedConnection.executeUpdate();
            preparedConnection.getGeneratedKeys().next();
            generatedKey = preparedConnection.getGeneratedKeys().getLong("id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement getStatement;
        try {
            getStatement = connection.prepareStatement("SELECT * FROM customer WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            getStatement.setLong(1, generatedKey);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer addedEntity = new Customer();
        try {
            ResultSet resultSet = getStatement.executeQuery();
            while (resultSet.next()) {
                addedEntity.setName(resultSet.getString("name"));
                addedEntity.setAge(resultSet.getInt("age"));
                addedEntity.setActive(resultSet.getBoolean("is_active"));
                addedEntity.setId(resultSet.getLong("id"));
                addedEntity.setAddress(addressRepository.findById(resultSet.getLong("address_id")).orElseThrow());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addedEntity;
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
