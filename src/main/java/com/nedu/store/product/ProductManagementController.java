package com.nedu.store.product;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.service.ProductManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductManagementController {

    private final ProductManagementService productMS;

    public ProductManagementController(ProductManagementService productMS) {
        this.productMS = productMS;
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody ProductDto productDto) throws RestException {
        return new ResponseEntity<>(productMS.add(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    public ResponseEntity<Long> delete(@PathVariable long id) throws RestException {
        return new ResponseEntity<>(productMS.delete(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable long id, @RequestBody ProductDto productDto)
            throws RestException {
        productDto.setId(id);
        return new ResponseEntity<>(productMS.update(productDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> show() throws RestException {
        return new ResponseEntity<>(productMS.show(), HttpStatus.OK);
    }
}
