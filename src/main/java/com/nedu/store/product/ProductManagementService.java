package com.nedu.store.product;

public interface ProductManagementService {
    boolean add(Product product);
    boolean delete(long id);
    void show();
}
