package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.product.ProductDto;
import com.nedu.store.product.ProductEntity;
import com.nedu.store.product.ProductMapper;
import com.nedu.store.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("productManagementService")
@Transactional
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductManagementServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("Product management service created");
    }

    @Override
    public long add(ProductDto productDto) throws RestException {
        log.debug("Adding product to store");

        try {
            ProductEntity stored = productRepository.saveAndFlush(productMapper.toEntity(productDto));

            log.info("Product=" + productDto + " was added to store");

            return stored.getId();

        } catch (Exception e) {
            log.warn("Adding product=" + productDto + " was failed!");
            throw new RestException(RestExceptionEnum.ERR_004);
        }
    }

    @Override
    public long delete(long id) throws RestException {

        if (productRepository.existsById(id)) {

            productRepository.deleteById(id);

            log.info("Product with id=" + id + " deleted from store");
            return id;
        } else {
            throw new RestException(RestExceptionEnum.ERR_004);
        }
    }

    @Override
    public ProductDto update(ProductDto productDto) throws RestException {
        log.debug("Updating product");

        try {

            ProductEntity stored = productRepository.findById(productDto.getId()).get();

            ProductEntity updated = productRepository.saveAndFlush(productMapper.toEntity(productDto));

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
    public List<ProductDto> show() throws RestException {
        log.debug("Showing store's products");

        try {

            List<ProductEntity> productEntityList = productRepository.findAll();

            log.info("Showing products");

            return productEntityList.stream().map(productMapper::toDto).collect(Collectors.toList());

        } catch (Exception e) {
            log.warn("Showing products was failed!");
            throw new RestException(RestExceptionEnum.ERR_005);
        }
    }
}
