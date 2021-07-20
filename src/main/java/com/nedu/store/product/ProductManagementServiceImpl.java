package com.nedu.store.product;

import com.nedu.store.product.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service("productManagementService")
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductDao productDao;

    public ProductManagementServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("Product management service created");
    }

    @Override
    public Product add(Product product) {
        log.debug("Adding product to store");

        Product stored = productDao.createProduct(product);

        if (null == stored) {
            log.warn("Adding product=" + product + " was failed!");
            throw new RuntimeException("Product=" + product + " wasn't added");
        }

        product = productDao.getProduct(stored.getId());

        log.info("Product=" + product + " was added to store");

        return product;
    }

    @Override
    public void delete(long id) {
        productDao.deleteProduct(id);

        log.info("Product with id=" + id + " deleted from store");
    }

    @Override
    public Product update(Product product) {
        try {
            log.debug("Updating product");

            Product updated = productDao.updateProduct(product);

            product = productDao.getProduct(updated.getId());

            log.info("Product(id=" + updated.getId()
                    + ") was updated to=" + product);

            return product;

        } catch (Exception e) {
            log.warn("Updating product with id="
                    + product.getId() + " was failed!");
            return null;
        }
    }

    @Override
    public void show() {
        try {
            log.debug("Showing store's products");

            productDao.getProductList().forEach(System.out::println);

        } catch (Exception e) {
            log.warn("Showing products was failed!");
        }
    }
}
