package com.nedu.store.product;

public interface ProductManagementService {
    Product add(Product product);
    void delete(long id);
    Product update(Product product);
    void show();
}
