package com.nedu.store.product.controller;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.Product;
import com.nedu.store.product.service.ProductManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductManagementControllerImpl implements ProductManagementController {

    private final ProductManagementService productMS;

    public ProductManagementControllerImpl(ProductManagementService productMS) {
        this.productMS = productMS;
    }

    @Override
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) throws RestException {
        return new ResponseEntity<>(productMS.add(product), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        productMS.delete(id);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @RequestBody Product product) throws RestException {
        product.setId(id);
        return new ResponseEntity<>(productMS.update(product), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Product>> show() throws RestException {
        return new ResponseEntity<>(productMS.show(), HttpStatus.OK);
    }
}
