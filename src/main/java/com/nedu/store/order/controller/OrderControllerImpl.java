package com.nedu.store.order.controller;

import com.nedu.store.order.service.OrderService;
import com.nedu.store.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    @PostMapping("/{id}")
    public void addToBasket(@RequestBody String login, @PathVariable("id") long productId) {
        orderService.addToBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteFromBasket(@RequestBody String login, @PathVariable("id") long productId) {
        orderService.deleteFromBasket(userService.getUserByLogin(login).getId(), productId);
    }

    @Override
    @PostMapping
    public void purchaseBasket(@RequestBody String login) {
        orderService.purchaseBasket(userService.getUserByLogin(login).getId());
    }
}
