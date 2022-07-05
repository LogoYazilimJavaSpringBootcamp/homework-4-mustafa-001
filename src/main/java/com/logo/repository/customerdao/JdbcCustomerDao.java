package com.logo.repository.customerdao;

import com.logo.model.Customer;
import com.logo.repository.AddressRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement getStatement;
        try {
            getStatement = connection.prepareStatement("SELECT * FROM customer WHERE name = ? ");
            getStatement.setString(1, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer entity;
        try {
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                entity = createFromResultSet(resultSet);
            } else {
                entity = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Optional<Customer> result;
        if (entity != null) {
            result = Optional.of(entity);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public List<Customer> getByIsActive(boolean isActive) {
        PreparedStatement getStatement;
        try {
            getStatement = connection.prepareStatement("SELECT * FROM customer WHERE is_active = ?");
            getStatement.setBoolean(1, isActive);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getCustomersFromStatement(getStatement);

    }

    @Override
    public Customer save(Customer entity) {
        long generatedKey;
        try {
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
        Customer addedEntity = null;
        try {
            ResultSet resultSet = getStatement.executeQuery();
            resultSet.next();
            addedEntity = createFromResultSet(resultSet);
            addedEntity.setAddress(entity.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addedEntity;
    }

    @Override
    public Customer update(Customer entity) {
        try {
            var preparedConnection = connection.prepareStatement("UPDATE customer SET name = ?, age = ?, is_active = ?, address_id = ? WHERE id = ?");
            preparedConnection.setString(1, entity.getName());
            preparedConnection.setInt(2, entity.getAge());
            preparedConnection.setBoolean(3, entity.isActive());
            preparedConnection.setLong(4, entity.getAddress().getId());
            preparedConnection.setLong(5, entity.getId());
            preparedConnection.executeUpdate();

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
            getStatement.setLong(1, entity.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer updatedEntity = null;
        try {
            ResultSet resultSet = getStatement.executeQuery();
            resultSet.next();
            updatedEntity = createFromResultSet(resultSet);
            updatedEntity.setAddress(entity.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedEntity;
    }

    @SneakyThrows
    private Customer createFromResultSet(ResultSet resultSet) {
        var entity = new Customer();
        entity.setName(resultSet.getString("name"));
        entity.setAge(resultSet.getInt("age"));
        entity.setActive(resultSet.getBoolean("is_active"));
        entity.setId(resultSet.getLong("id"));
        entity.setAddress(addressRepository.findById(resultSet.getLong("address_id")).orElse(null));
        return entity;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        PreparedStatement getStatement;
        try {
            getStatement = connection.prepareStatement("SELECT * FROM customer WHERE id = ? ");
            getStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer addedEntity = null;
        try {
            ResultSet resultSet = getStatement.executeQuery();
            while (resultSet.next()) {
                addedEntity = createFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Optional<Customer> result;
        if (addedEntity != null) {
            result = Optional.of(addedEntity);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public List<Customer> findAll() {
        PreparedStatement getStatement;
        try {
            getStatement = connection.prepareStatement("SELECT * FROM customer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getCustomersFromStatement(getStatement);
    }

    private List<Customer> getCustomersFromStatement(PreparedStatement getStatement) {
        List<Customer> entities = new ArrayList<>();
        try {
            ResultSet resultSet = getStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    @Override
    public void delete(Customer entity) {
        try {
            var preparedConnection = connection.prepareStatement("DELETE FROM customer WHERE id = ? ");
            preparedConnection.setLong(1, entity.getId());
            preparedConnection.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
