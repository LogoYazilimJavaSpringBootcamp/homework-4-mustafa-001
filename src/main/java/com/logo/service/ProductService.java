package com.logo.service;

import com.logo.model.Product;
import com.logo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product request) {
        return productRepository.save(request);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByBarcode(String code) {
        return productRepository.findByBarcode(code);
    }

    public List<Product> getProductsStartingWith(String searchQuery) {
        return productRepository.getProductsStartingWith(searchQuery);
    }

    public Product update(int id, Product newProduct) {
        System.out.println("Updating product: " + id + "  to " + newProduct.toString());
        var oldServiceOpt = productRepository.findById(id);
        if (oldServiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldProduct = oldServiceOpt.get();
        if (!newProduct.getName().equals("")) {
            oldProduct.setName(newProduct.getName());
        }
        if (!newProduct.isActive() == oldProduct.isActive()) oldProduct.setActive(newProduct.isActive());
        if (newProduct.getBarcode() != null) oldProduct.setBarcode(newProduct.getBarcode());
        if (newProduct.getCESSRate() != null) oldProduct.setCESSRate(newProduct.getCESSRate());
        if (newProduct.getCurrency() != null) oldProduct.setCurrency(newProduct.getCurrency());
        if (newProduct.getPurchasePrice() != null)
            oldProduct.setPurchasePrice(newProduct.getPurchasePrice());
        if (newProduct.getSalesPrice() != null)
            oldProduct.setSalesPrice(newProduct.getSalesPrice());
        if (newProduct.getUnitType() != null) oldProduct.setUnitType(newProduct.getUnitType());
        if (newProduct.getVatRate() != null) oldProduct.setVatRate(newProduct.getVatRate());
        return oldProduct;
    }

    public void delete(int id) {
        System.out.println("Deleting service: " + id);
        var serviceOpt = productRepository.findById(id);
        if (serviceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        productRepository.delete(serviceOpt.get());
    }

}
