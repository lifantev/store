package com.nedu.store.order.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.order.BasketEntity;
import com.nedu.store.order.BasketRepository;
import com.nedu.store.product.ProductDto;
import com.nedu.store.product.ProductEntity;
import com.nedu.store.product.ProductRepository;
import com.nedu.store.user.UserEntity;
import com.nedu.store.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(UserRepository userRepository, BasketRepository basketRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("Order service created");
    }

    @Override
    public void addToBasket(String login, long productId) throws RestException {
        try {
            log.debug("Adding product to basket");

            UserEntity user = userRepository.findByLogin(login).get();
            ProductEntity productEntity = productRepository.findById(productId).get();
            BasketEntity basket = user.getBasket();

            basket.getProducts().add(productEntity);
            basketRepository.saveAndFlush(basket);

            log.info("Product with id=" + productId +
                    " added to user's(" + login + ") basket");

        } catch (Exception exception) {
            log.warn("Adding product with id=" + productId
                    + " to user's(" + login + ") basket was failed!");
            throw new RestException(RestExceptionEnum.ERR_002);
        }
    }

    @Override
    public void deleteFromBasket(String login, long productId) throws RestException {
        try {
            log.debug("Deleting product from basket");

            UserEntity user = userRepository.findByLogin(login).get();
            ProductEntity productEntity = productRepository.findById(productId).get();
            BasketEntity basket = user.getBasket();

            basket.getProducts().remove(productEntity);
            basketRepository.saveAndFlush(basket);

            log.info("Product with id=" + productId +
                    " deleted from user's(" + login + ") basket");

        } catch (Exception exception) {
            log.warn("Deleting product with id=" + productId +
                    " from user's(" + login + ") basket was failed!");
            throw new RestException(RestExceptionEnum.ERR_002);
        }
    }
}
