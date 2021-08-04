package com.nedu.store.order;

import com.nedu.store.product.ProductEntity;
import com.nedu.store.user.UserEntity;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "products",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private List<ProductEntity> products;

    @OneToOne(mappedBy = "basket")
    private UserEntity user;
}
