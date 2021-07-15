package com.nedu.store.product;

import lombok.*;

@Data
@Builder
public class Product {
    long id;
    String name;
    double cost;
}