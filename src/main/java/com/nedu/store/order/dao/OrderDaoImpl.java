package com.nedu.store.order.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.Basket;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger("orderDao");

    private final Map<Long, Basket> baskets = new HashMap<>();

    private IdGenerator idGenerator;

    public OrderDaoImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Basket createBasket(Basket basket) {
        long id = idGenerator.getId();

        Basket toStore = Basket.builder()
                .id(id)
                .productIds(new LinkedList<>())
                .build();

        baskets.put(id, toStore);

        basket.setId(id);
        LOGGER.trace("Basket created=" + basket);
        return basket;
    }

    @Override
    public Basket getBasket(long basketId) {
        Basket stored = baskets.get(basketId);

        if (null == stored) {
            LOGGER.warn("Get basket with id=" + basketId + " was failed");
            throw new RuntimeException("There is no basket with id=" + basketId);
        }

        LOGGER.trace("Basket accessed=" + stored);
        return Basket.builder()
                .id(stored.getId())
                .productIds(stored.getProductIds())
                .totalCost(stored.getTotalCost())
                .purchaseSuccess(stored.isPurchaseSuccess())
                .build();
    }

    @Override
    public Basket updateBasket(Basket basket) {
        Basket origin = baskets.get(basket.getId());

        if (null == origin) {
            LOGGER.warn("Update basket with id=" + basket.getId() + " was failed");
            throw new RuntimeException("There is no basket with id=" + basket.getId());
        }

        origin.setProductIds(basket.getProductIds());
        origin.setTotalCost(basket.getTotalCost());
        origin.setPurchaseSuccess(true);

        basket.setId(origin.getId());
        LOGGER.trace("Basket updated=" + basket);
        return basket;
    }
}
