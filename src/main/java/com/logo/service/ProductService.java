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
        return productRepository.getAll();
    }

    public Optional<Product> getProductByBarcode(String code) {
        return productRepository.findByBarcode(code);
    }

    public List<Product> getProductsStartingWith(String searchQuery) {
        return productRepository.getProductsStartingWith(searchQuery);
    }

    public Product update(int id, Product service) {
        System.out.println("Updating service: " + id + "  to " + service.toString());
        var oldServiceOpt = productRepository.findById(id);
        if (oldServiceOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var oldService = oldServiceOpt.get();
        if (!service.getName().equals("")) {
            oldService.setName(service.getName());
        }
        if (!service.isActive() == oldService.isActive()) oldService.setActive(service.isActive());
        if (!service.getBarcode().equals(oldService.getBarcode())) oldService.setBarcode(service.getBarcode());
        if (!service.getCESSRate().equals(oldService.getCESSRate())) oldService.setCESSRate(service.getCESSRate());
        if (!service.getCurrency().equals(oldService.getCurrency())) oldService.setCurrency(service.getCurrency());
        if (!service.getPurchasePrice().equals(oldService.getPurchasePrice()))
            oldService.setPurchasePrice(service.getPurchasePrice());
        if (!service.getSalesPrice().equals(oldService.getSalesPrice()))
            oldService.setSalesPrice(service.getSalesPrice());
        if (!service.getUnitType().equals(oldService.getUnitType())) oldService.setUnitType(service.getUnitType());
        if (!service.getVatRate().equals(oldService.getVatRate())) oldService.setVatRate(service.getVatRate());
        return oldService;
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
