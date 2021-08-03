package com.nedu.store.product.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.product.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("productDao")
public class ProductDaoImpl implements ProductDao {

    private final Map<Long, ProductDTO> products = new HashMap<>();
    private final IdGenerator idGenerator;

    public ProductDaoImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        long id = idGenerator.getId();

        ProductDTO productDTOToStore = ProductDTO.builder()
                .id(id)
                .name(productDto.getName())
                .cost(productDto.getCost())
                .build();

        products.put(id, productDTOToStore);

        productDto.setId(id);
        log.trace("Product created=" + productDto);
        return productDto;
    }

    @Override
    public ProductDTO getProduct(long id) {
        ProductDTO stored = products.get(id);

        if (null == stored) {
            log.warn("Get product with id=" + id + " was failed");
            throw new RuntimeException("There is no product with id=" + id);
        }

        log.trace("Product accessed=" + stored);
        return ProductDTO.builder()
                .id(stored.getId())
                .name(stored.getName())
                .cost(stored.getCost())
                .build();
    }

    @Override
    public List<ProductDTO> getProductList() {
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
    public ProductDTO updateProduct(ProductDTO productDto) {
        ProductDTO origin = products.get(productDto.getId());

        if (null == origin) {
            log.warn("Update product with id=" + productDto.getId() + " was failed");
            throw new RuntimeException("There is no product with id=" + productDto.getId());
        }

        origin.setName(productDto.getName());
        origin.setCost(productDto.getCost());

        productDto.setId(origin.getId());
        log.trace("Product updated=" + productDto);
        return productDto;
    }
}
