package com.nedu.store.order.service;

public interface OrderService {
    void addToBasket(long userId, long productId);
    void deleteFromBasket(long userId, long productId);
    void purchaseBasket(long userId);
}