package com.nedu.store.product.dao;

import com.nedu.store.product.Product;

import java.util.List;

public interface ProductDao {
    Product createProduct(Product product);
    Product getProduct(long id);
    List<Product> getProductList();
    void deleteProduct(long id);
    Product updateProduct(Product product);
}
