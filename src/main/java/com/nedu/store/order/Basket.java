package com.nedu.store.order;

import com.nedu.store.product.Product;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Basket {
    @NonNull List<Product> products;
    double totalCost;
    boolean purchaseSuccess;
}
