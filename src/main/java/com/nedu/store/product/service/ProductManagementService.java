package com.nedu.store.product.service;

import com.nedu.store.product.Product;

import java.util.List;

public interface ProductManagementService {
    Product add(Product product);
    void delete(long id);
    Product update(Product product);
    List<Product> show();
}