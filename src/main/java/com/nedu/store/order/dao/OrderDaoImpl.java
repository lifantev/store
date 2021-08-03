package com.nedu.store.order.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.BasketDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
@Service("orderDao")
public class OrderDaoImpl implements OrderDao {

    private final Map<Long, BasketDTO> baskets = new HashMap<>();

    private final IdGenerator idGenerator;

    public OrderDaoImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public BasketDTO createBasket(BasketDTO basketDTO) {
        long id = idGenerator.getId();

        BasketDTO toStore = BasketDTO.builder()
                .id(id)
                .productIds(new LinkedList<>())
                .build();

        baskets.put(id, toStore);

        basketDTO.setId(id);
        log.trace("Basket created=" + basketDTO);
        return basketDTO;
    }

    @Override
    public BasketDTO getBasket(long basketId) {
        BasketDTO stored = baskets.get(basketId);

        if (null == stored) {
            log.warn("Get basket with id=" + basketId + " was failed");
            throw new RuntimeException("There is no basket with id=" + basketId);
        }

        log.trace("Basket accessed=" + stored);
        return BasketDTO.builder()
                .id(stored.getId())
                .productIds(stored.getProductIds())
                .totalCost(stored.getTotalCost())
                .build();
    }

    @Override
    public BasketDTO updateBasket(BasketDTO basketDTO) {
        BasketDTO origin = baskets.get(basketDTO.getId());

        if (null == origin) {
            log.warn("Update basket with id=" + basketDTO.getId() + " was failed");
            throw new RuntimeException("There is no basket with id=" + basketDTO.getId());
        }

        origin.setProductIds(basketDTO.getProductIds());
        origin.setTotalCost(basketDTO.getTotalCost());

        basketDTO.setId(origin.getId());
        log.trace("Basket updated=" + basketDTO);
        return basketDTO;
    }
}
