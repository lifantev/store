package com.nedu.store.order.service;

import com.nedu.store.exceptions.RestException;

public interface OrderService {
    void addToBasket(String login, long productId) throws RestException;
    void deleteFromBasket(String login, long productId) throws RestException;
}