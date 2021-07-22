package com.nedu.store.order.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.Basket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
@Service("orderDao")
public class OrderDaoImpl implements OrderDao {

    private final Map<Long, Basket> baskets = new HashMap<>();

    private final IdGenerator idGenerator;

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
        log.trace("Basket created=" + basket);
        return basket;
    }

    @Override
    public Basket getBasket(long basketId) {
        Basket stored = baskets.get(basketId);

        if (null == stored) {
            log.warn("Get basket with id=" + basketId + " was failed");
            throw new RuntimeException("There is no basket with id=" + basketId);
        }

        log.trace("Basket accessed=" + stored);
        return Basket.builder()
                .id(stored.getId())
                .productIds(stored.getProductIds())
                .totalCost(stored.getTotalCost())
                .build();
    }

    @Override
    public Basket updateBasket(Basket basket) {
        Basket origin = baskets.get(basket.getId());

        if (null == origin) {
            log.warn("Update basket with id=" + basket.getId() + " was failed");
            throw new RuntimeException("There is no basket with id=" + basket.getId());
        }

        origin.setProductIds(basket.getProductIds());
        origin.setTotalCost(basket.getTotalCost());

        basket.setId(origin.getId());
        log.trace("Basket updated=" + basket);
        return basket;
    }
}
