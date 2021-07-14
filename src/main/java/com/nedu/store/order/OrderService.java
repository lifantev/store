package com.nedu.store.order;

import com.nedu.store.product.Product;

public interface OrderService {
    boolean addToBasket(Basket basket, Product product);
    boolean deleteFromBasket(Basket basket, Product product);
    boolean purchaseBasket(Basket basket, boolean purchaseSuccess);
}