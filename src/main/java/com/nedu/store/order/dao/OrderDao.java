package com.nedu.store.order.dao;

import com.nedu.store.order.Basket;

public interface OrderDao {
    Basket createBasket(Basket basket);
    Basket getBasket(long basketId);
    Basket updateBasket(Basket basket);
}
