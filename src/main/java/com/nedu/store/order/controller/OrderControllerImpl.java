package com.nedu.store.order.controller;

import com.nedu.store.order.service.OrderService;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToBasket(@RequestBody String login, @PathVariable("id") long productId) {
        orderService.addToBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromBasket(@RequestBody String login, @PathVariable("id") long productId) {
        orderService.deleteFromBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purchaseBasket(@RequestBody String login) {
        orderService.purchaseBasket(userService.getUserByLogin(login).getId());
    }
}
