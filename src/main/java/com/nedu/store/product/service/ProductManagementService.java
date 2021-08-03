package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.ProductDTO;

import java.util.List;

public interface ProductManagementService {
    ProductDTO add(ProductDTO productDto) throws RestException;
    void delete(long id);
    ProductDTO update(ProductDTO productDto) throws RestException;
    List<ProductDTO> show() throws RestException;
}
