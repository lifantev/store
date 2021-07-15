package com.nedu.store.product;

import com.nedu.store.product.dao.ProductDao;
import org.apache.log4j.Logger;

public class ProductManagementServiceImpl implements ProductManagementService {
    private static final Logger LOGGER = Logger.getLogger("productManagementService");

    private final ProductDao productDao;

    public ProductManagementServiceImpl(ProductDao productDao) {
        this.productDao = productDao;

        LOGGER.debug("Product management service created");
    }

    @Override
    public Product add(Product product) {
        LOGGER.debug("Adding product to store");

        Product stored = productDao.createProduct(product);

        if (null == stored) {
            LOGGER.warn("Adding product=" + product + " was failed!");
            throw new RuntimeException("Product=" + product + " wasn't added");
        }

        product = productDao.getProduct(stored.getId());

        LOGGER.info("Product=" + product + " was added to store");

        return product;
    }

    @Override
    public void delete(long id) {
        productDao.deleteProduct(id);

        LOGGER.info("Product with id=" + id + " deleted from store");
    }

    @Override
    public Product update(Product product) {
        try {
            LOGGER.debug("Updating product");

            Product updated = productDao.updateProduct(product);

            product = productDao.getProduct(updated.getId());

            LOGGER.info("Product(id=" + updated.getId()
                    + ") was updated to=" + product);

            return product;

        } catch (Exception e) {
            LOGGER.warn("Updating product with id="
                    + product.getId() + " was failed!");
            return null;
        }
    }

    @Override
    public void show() {
        try {
            LOGGER.debug("Showing store's products");

            productDao.getProductList().forEach(System.out::println);

        } catch (Exception e) {
            LOGGER.warn("Showing products was failed!");
        }
    }
}
