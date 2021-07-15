package com.nedu.store.product.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.product.Product;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger("productDao");

    private final Map<Long, Product> products = new HashMap<>();
    private final IdGenerator idGenerator;

    public ProductDaoImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Product createProduct(Product product) {
        long id = idGenerator.getId();

        Product productToStore = Product.builder()
                .id(id)
                .name(product.getName())
                .cost(product.getCost())
                .build();

        products.put(id, productToStore);

        product.setId(id);
        return product;
    }

    @Override
    public Product getProduct(long id) {
        Product stored = products.get(id);

        if (stored == null) {
            throw new RuntimeException("There is no product with id=" + id);
        }

        return Product.builder()
                .id(stored.getId())
                .name(stored.getName())
                .build();
    }

    @Override
    public List<Product> getProductList() {
        var storedProducts = products.entrySet();

        if (storedProducts == null) {
            throw new RuntimeException("There is no products at all=");
        }

        return storedProducts.stream().map(e -> getProduct(e.getKey())).toList();
    }

    @Override
    public void deleteProduct(long id) {
        products.remove(id);
    }

    @Override
    public Product updateProduct(Product product) {
        Product origin = products.get(product.getId());

        if (origin == null) {
            throw new RuntimeException("There is no product with id=" + product.getId());
        }

        origin.setName(product.getName());
        origin.setCost(product.getCost());

        product.setId(origin.getId());
        return product;
    }
}
