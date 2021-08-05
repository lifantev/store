package com.nedu.store.order.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.product.ProductDto;

import java.util.List;

public interface OrderService {
    void addToBasket(String login, long productId) throws RestException;
    void deleteFromBasket(String login, long productId) throws RestException;
    List<ProductDto>showBasket(String login) throws RestException;
}