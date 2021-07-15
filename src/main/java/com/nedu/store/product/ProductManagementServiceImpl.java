package com.nedu.store.product;

import com.nedu.store.product.dao.ProductDao;
import org.apache.log4j.Logger;

public class ProductManagementServiceImpl implements ProductManagementService {
    private static final Logger LOGGER = Logger.getLogger("productManagementService");

    private final ProductDao productDao;

    public ProductManagementServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product add(Product product) {
        return productDao.createProduct(product);
    }

    @Override
    public void delete(long id) {
        productDao.deleteProduct(id);
    }

    @Override
    public Product update(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public void show() {
        productDao.getProductList().forEach(System.out::println);
    }
}
