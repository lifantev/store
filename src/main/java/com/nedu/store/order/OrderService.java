package com.nedu.store.order;

public interface OrderService {
    void addToBasket(long userId, long productId);
    void deleteFromBasket(long userId, long productId);
    void purchaseBasket(long userId, boolean purchaseSuccess);
}