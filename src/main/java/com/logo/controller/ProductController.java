package com.logo.controller;

import com.logo.model.Product;
import com.logo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/name")
    public List<Product> getByName(@RequestParam String name) {
        return productService.getProductsStartingWith(name);
    }

    @GetMapping("/code/{code}")
    public Product getById(@PathVariable String code) {
        var product = productService.getProductByBarcode(code);
        return product.orElse(null);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable int id) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }


}
