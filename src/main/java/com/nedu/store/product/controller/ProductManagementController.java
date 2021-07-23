package com.nedu.store.product.controller;

import com.nedu.store.product.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductManagementController {
    ResponseEntity<Product> add(Product product);
    void delete(long id);
    ResponseEntity<Product> update(long id, Product product);
    ResponseEntity<List<Product>> show();
}
