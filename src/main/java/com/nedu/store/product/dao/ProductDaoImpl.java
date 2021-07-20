package com.nedu.store.product.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("productDao")
public class ProductDaoImpl implements ProductDao {

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
        log.trace("Product created=" + product);
        return product;
    }

    @Override
    public Product getProduct(long id) {
        Product stored = products.get(id);

        if (null == stored) {
            log.warn("Get product with id=" + id + " was failed");
            throw new RuntimeException("There is no product with id=" + id);
        }

        log.trace("Product accessed=" + stored);
        return Product.builder()
                .id(stored.getId())
                .name(stored.getName())
                .cost(stored.getCost())
                .build();
    }

    @Override
    public List<Product> getProductList() {
        var storedProducts = products.entrySet();

        if (storedProducts.isEmpty()) {
            log.warn("Getting product list was failed");
            throw new RuntimeException("There is no products in product list");
        }

        log.trace("Product list accessed");
        return storedProducts.stream().map(e -> getProduct(e.getKey())).toList();
    }

    @Override
    public void deleteProduct(long id) {
        products.remove(id);
    }

    @Override
    public Product updateProduct(Product product) {
        Product origin = products.get(product.getId());

        if (null == origin) {
            log.warn("Update product with id=" + product.getId() + " was failed");
            throw new RuntimeException("There is no product with id=" + product.getId());
        }

        origin.setName(product.getName());
        origin.setCost(product.getCost());

        product.setId(origin.getId());
        log.trace("Product updated=" + product);
        return product;
    }
}
