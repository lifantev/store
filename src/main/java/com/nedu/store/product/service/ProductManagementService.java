package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.Product;

import java.util.List;

public interface ProductManagementService {
    Product add(Product product) throws RestException;
    void delete(long id);
    Product update(Product product) throws RestException;
    List<Product> show() throws RestException;
}
