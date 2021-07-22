package com.nedu.store.product.controller;

import com.nedu.store.product.Product;

import java.util.List;

public interface ProductManagementController {
    void add(Product product);
    void delete(long id);
    void update(long id, Product product);
    List<Product> show();
}
