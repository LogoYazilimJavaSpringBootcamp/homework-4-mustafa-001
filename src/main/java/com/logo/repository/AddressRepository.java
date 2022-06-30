package com.logo.repository;

import com.logo.model.Address;
import com.logo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(long id);

    default Address update(Address address) {
        var existingAddress = this.findById(address.getId()).get();
        if (address.getCountry() != null)
            existingAddress.setCountry(address.getCountry());
        if (address.getProvince() != null)
            existingAddress.setProvince(address.getProvince());
        if (address.getAddress() != null)
            existingAddress.setAddress(address.getAddress());
        return this.save(existingAddress);
    }
}

