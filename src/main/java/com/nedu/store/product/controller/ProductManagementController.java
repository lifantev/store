package com.nedu.store.product.controller;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductManagementController {
    ResponseEntity<Product> add(Product product) throws RestException;
    void delete(long id);
    ResponseEntity<Product> update(long id, Product product) throws RestException;
    ResponseEntity<List<Product>> show() throws RestException;
}
