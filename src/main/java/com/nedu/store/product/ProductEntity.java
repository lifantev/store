package com.nedu.store.product;

import com.nedu.store.order.BasketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "products", schema = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", unique = true, columnDefinition = "VARCHAR(400)")
    private String name;

    @Column(name = "cost", columnDefinition = "FLOAT8")
    private Double cost;

    @ManyToMany(mappedBy = "products")
    private List<BasketEntity> baskets = new LinkedList<>();
}
