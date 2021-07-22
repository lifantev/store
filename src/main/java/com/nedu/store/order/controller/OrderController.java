package com.nedu.store.order.controller;

public interface OrderController {
    void addToBasket(String login, long productId);
    void deleteFromBasket(String login, long productId);
    void purchaseBasket(String login);
}
