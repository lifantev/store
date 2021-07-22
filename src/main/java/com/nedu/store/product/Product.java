package com.nedu.store.product;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    long id;
    String name;
    double cost;
}