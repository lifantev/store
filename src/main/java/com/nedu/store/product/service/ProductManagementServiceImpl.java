package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.product.ProductDTO;
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
    public ProductDTO add(ProductDTO productDto) throws RestException {
        log.debug("Adding product to store");

        ProductDTO stored = productDao.createProduct(productDto);

        if (null == stored) {
            log.warn("Adding product=" + productDto + " was failed!");
            throw new RestException(RestExceptionEnum.ERR_004);
        }

        productDto = productDao.getProduct(stored.getId());

        log.info("Product=" + productDto + " was added to store");

        return productDto;
    }

    @Override
    public void delete(long id) {
        productDao.deleteProduct(id);

        log.info("Product with id=" + id + " deleted from store");
    }

    @Override
    public ProductDTO update(ProductDTO productDto) throws RestException {
        try {
            log.debug("Updating product");

            ProductDTO updated = productDao.updateProduct(productDto);

            productDto = productDao.getProduct(updated.getId());

            log.info("Product(id=" + updated.getId()
                    + ") was updated to=" + productDto);

            return productDto;

        } catch (Exception e) {
            log.warn("Updating product with id="
                    + productDto.getId() + " was failed!");
            throw new RestException(RestExceptionEnum.ERR_004);
        }
    }

    @Override
    public List<ProductDTO> show() throws RestException {
        try {
            log.debug("Showing store's products");

            List<ProductDTO> productDTOList = productDao.getProductList();

            log.info("Showing products {{}}", productDTOList);

            return productDTOList;

        } catch (Exception e) {
            log.warn("Showing products was failed!");
            throw new RestException(RestExceptionEnum.ERR_005);
        }
    }
}
