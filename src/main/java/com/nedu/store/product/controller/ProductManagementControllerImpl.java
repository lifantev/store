package com.nedu.store.product.controller;

import com.nedu.store.product.Product;
import com.nedu.store.product.service.ProductManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductManagementControllerImpl implements ProductManagementController {

    private final ProductManagementService productMS;

    public ProductManagementControllerImpl(ProductManagementService productMS) {
        this.productMS = productMS;
    }

    @Override
    @PostMapping
    public void add(@RequestBody Product product) {
        productMS.add(product);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productMS.delete(id);
    }

    @Override
    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        productMS.update(product);
    }

    @Override
    @GetMapping
    public List<Product> show() {
        return productMS.show();
    }
}
