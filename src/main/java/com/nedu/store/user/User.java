package com.nedu.store.user;

import com.nedu.store.order.Basket;
import lombok.*;

import java.util.Objects;

@Data
@Builder
public class User {
    long id;
    String login;
    String password;
    long basketId;
}