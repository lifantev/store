package com.nedu.store.order.dao;

import com.nedu.store.order.BasketDTO;

public interface OrderDao {
    BasketDTO createBasket(BasketDTO basketDTO);
    BasketDTO getBasket(long basketId);
    BasketDTO updateBasket(BasketDTO basketDTO);
}
