package com.nedu.store.product;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class ProductManagementServiceImpl implements ProductManagementService {
    private final List<Product> productList = new LinkedList<>();
    private long id = 0;

    @Override
    public boolean add(Product product) {
        product.setId(id);
        id++;
        return productList.add(product);
    }

    @Override
    public boolean delete(long id) {
        Product product = new Product();
        product.setId(id);
        return productList.remove(product);
    }

    @Override
    public void show() {
        if (!productList.isEmpty()) {
            productList.forEach(System.out::println);
        }
    }
}
