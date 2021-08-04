package com.nedu.store.product;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductMapper(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    public ProductDto toDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public ProductEntity toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }
}
