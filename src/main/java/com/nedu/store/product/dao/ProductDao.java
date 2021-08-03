package com.nedu.store.product.dao;

import com.nedu.store.product.ProductDTO;

import java.util.List;

public interface ProductDao {
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO getProduct(long id);
    List<ProductDTO> getProductList();
    void deleteProduct(long id);
    ProductDTO updateProduct(ProductDTO productDto);
}
