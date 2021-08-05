package com.nedu.store.order;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.order.service.OrderService;
import com.nedu.store.product.ProductDto;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToBasket(Authentication authentication, @PathVariable("id") long productId)
            throws RestException {
        orderService.addToBasket(authentication.getName(), productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromBasket(Authentication authentication, @PathVariable("id") long productId)
            throws RestException {
        orderService.deleteFromBasket(authentication.getName(), productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> showBasket(Authentication authentication)
            throws RestException {
        return new ResponseEntity<>(orderService.showBasket(authentication.getName()), HttpStatus.OK);
    }
}
