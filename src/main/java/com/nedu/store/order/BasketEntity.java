package com.nedu.store.order;

import com.nedu.store.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "baskets", schema = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
    private List<ProductEntity> productList;
}
