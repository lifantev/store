package com.nedu.store.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String login;
    private String password;
}