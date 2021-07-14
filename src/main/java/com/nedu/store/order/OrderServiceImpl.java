package com.nedu.store.order;

import com.nedu.store.product.Product;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addToBasket(Basket basket, Product product) {
        if (basket.getProducts().add(product)) {
            basket.setTotalCost(product.getCost() + basket.getTotalCost());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteFromBasket(Basket basket, Product product) {
        if (basket.getProducts().remove(product)) {
            basket.setTotalCost(basket.getTotalCost() - product.getCost());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean purchaseBasket(Basket basket, boolean purchaseSuccess) {
        if (purchaseSuccess == true) {
            basket.getProducts().clear();
            basket.setTotalCost(0);
        }
        return purchaseSuccess;
    }
}
