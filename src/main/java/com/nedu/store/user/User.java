package com.nedu.store.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    long id;
    String login;
    String password;
    long basketId;
}