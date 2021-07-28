package com.nedu.store.order.controller;

import com.nedu.store.exceptions.RestException;

public interface OrderController {
    void addToBasket(String login, long productId) throws RestException;
    void deleteFromBasket(String login, long productId) throws RestException;
    void purchaseBasket(String login) throws RestException;
}
