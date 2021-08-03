package com.nedu.store.product.controller;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.ProductDTO;
import com.nedu.store.product.service.ProductManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductManagementControllerImpl {

    private final ProductManagementService productMS;

    public ProductManagementControllerImpl(ProductManagementService productMS) {
        this.productMS = productMS;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO productDto) throws RestException {
        return new ResponseEntity<>(productMS.add(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        productMS.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable long id, @RequestBody ProductDTO productDto) throws RestException {
        productDto.setId(id);
        return new ResponseEntity<>(productMS.update(productDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> show() throws RestException {
        return new ResponseEntity<>(productMS.show(), HttpStatus.OK);
    }
}
