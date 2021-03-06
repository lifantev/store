package com.nedu.store.product.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.ProductDto;

import java.util.List;

public interface ProductManagementService {
    long add(ProductDto productDto) throws RestException;
    long delete(long id) throws RestException;
    ProductDto update(ProductDto productDto) throws RestException;
    List<ProductDto> show() throws RestException;
}
