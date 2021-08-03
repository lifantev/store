package com.nedu.store.order.controller;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.order.service.OrderService;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerImpl {

    private final OrderService orderService;
    private final UserService userService;

    public OrderControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToBasket(@RequestBody String login, @PathVariable("id") long productId)
            throws RestException {
        orderService.addToBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromBasket(@RequestBody String login, @PathVariable("id") long productId)
            throws RestException {
        orderService.deleteFromBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purchaseBasket(@RequestBody String login) throws RestException {
        orderService.purchaseBasket(userService.getUserByLogin(login).getId());
    }
}
