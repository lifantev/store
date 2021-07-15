package com.nedu.store.user;

import lombok.*;

@Data
@Builder
public class User {
    long id;
    String login;
    String password;
    long basketId;
}