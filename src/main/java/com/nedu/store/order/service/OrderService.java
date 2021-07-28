package com.nedu.store.order.service;

import com.nedu.store.exceptions.RestException;

public interface OrderService {
    void addToBasket(long userId, long productId) throws RestException;
    void deleteFromBasket(long userId, long productId) throws RestException;
    void purchaseBasket(long userId) throws RestException;
}