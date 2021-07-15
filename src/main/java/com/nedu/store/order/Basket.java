package com.nedu.store.order;

import com.nedu.store.product.dao.ProductDao;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Basket {
    long id;
    List<Long> productIds;
    double totalCost;
    boolean purchaseSuccess;
}
