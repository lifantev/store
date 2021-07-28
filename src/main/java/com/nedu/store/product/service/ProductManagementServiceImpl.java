package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.product.Product;
import com.nedu.store.product.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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
    public Product add(Product product) throws RestException {
        log.debug("Adding product to store");

        Product stored = productDao.createProduct(product);

        if (null == stored) {
            log.warn("Adding product=" + product + " was failed!");
            throw new RestException(RestExceptionEnum.ERR_004);
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
    public Product update(Product product) throws RestException {
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
            throw new RestException(RestExceptionEnum.ERR_004);
        }
    }

    @Override
    public List<Product> show() throws RestException {
        try {
            log.debug("Showing store's products");

            List<Product> productList = productDao.getProductList();

            log.info("Showing products {{}}", productList);

            return productList;

        } catch (Exception e) {
            log.warn("Showing products was failed!");
            throw new RestException(RestExceptionEnum.ERR_005);
        }
    }
}
