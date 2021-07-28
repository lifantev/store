package com.nedu.store.order.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.order.Basket;
import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.product.Product;
import com.nedu.store.product.dao.ProductDao;
import com.nedu.store.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderServiceImpl(UserDao userDao, OrderDao orderDao, ProductDao productDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("Order service created");
    }

    @Override
    public void addToBasket(long userId, long productId) throws RestException {
        try {
            log.debug("Adding product to basket");

            Basket basket =
                    orderDao.getBasket(userDao.getUser(userId).getBasketId());
            Product product = productDao.getProduct(productId);

            basket.getProductIds().add(product.getId());

            log.info("Product with id=" + productId +
                    " added to user's(id=" + userId + ") basket");

        } catch (Exception exception) {
            log.warn("Adding product with id=" + productId
                    + " to user's(id=" + userId + ") basket was failed!");
            throw new RestException(RestExceptionEnum.ERR_002);
        }
    }

    @Override
    public void deleteFromBasket(long userId, long productId) throws RestException {
        try {
            log.debug("Deleting product from basket");

            Basket basket =
                    orderDao.getBasket(userDao.getUser(userId).getBasketId());
            Product product = productDao.getProduct(productId);

            basket.getProductIds().remove(product.getId());

            log.info("Product with id=" + productId +
                    " deleted from user's(id=" + userId + ") basket");

        } catch (Exception exception) {
            log.warn("Deleting product with id=" + productId +
                    " from user's(id=" + userId + ") basket was failed!");
            throw new RestException(RestExceptionEnum.ERR_002);
        }
    }

    @Override
    public void purchaseBasket(long userId) throws RestException {
        log.debug("Purchasing basket");

        try {
            Basket basket =
                    orderDao.getBasket(userDao.getUser(userId).getBasketId());

            orderDao.updateBasket(Basket.builder()
                    .id(basket.getId())
                    .build()
            );

            log.info("User's(id=" + userId + ") basket was purchased");

        } catch (Exception exception) {
            log.warn("Purchasing basket of user(id=" + userId + ") was failed!");
            throw new RestException(RestExceptionEnum.ERR_003);
        }
    }
}
