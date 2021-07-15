package com.nedu.store.order;

import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.product.Product;
import com.nedu.store.product.dao.ProductDao;
import com.nedu.store.user.User;
import com.nedu.store.user.dao.UserDao;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger("orderService");

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderServiceImpl(UserDao userDao, OrderDao orderDao, ProductDao productDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @Override
    public void addToBasket(long userId, long productId) {
        try {
            Basket basket =
                    orderDao.getBasket(userDao.getUser(userId).getBasketId());
            Product product = productDao.getProduct(productId);

            basket.getProductIds().add(productId);
        } catch (Exception exception) {
            //


        }
    }

    @Override
    public void deleteFromBasket(long userId, long productId) {
        try {
            Basket basket =
                    orderDao.getBasket(userDao.getUser(userId).getBasketId());
            Product product = productDao.getProduct(productId);

            basket.getProductIds().remove(productId);
        } catch (Exception exception) {
            //


        }
    }

    @Override
    public void purchaseBasket(long userId, boolean purchaseSuccess) {
        if (purchaseSuccess == true) {
            try {
                Basket basket =
                        orderDao.getBasket(userDao.getUser(userId).getBasketId());

                orderDao.updateBasket(Basket.builder()
                        .id(basket.getId())
                        .build()
                );
            } catch (Exception exception) {
                //


            }
        }
    }
}
