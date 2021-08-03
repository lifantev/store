package com.nedu.store.user;

import com.nedu.store.order.BasketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "login", columnDefinition = "VARCHAR(400)")
    String login;

    @Column(name = "password", columnDefinition = "VARCHAR(400)")
    String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    BasketEntity basket;
}
