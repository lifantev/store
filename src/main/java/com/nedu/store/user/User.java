package com.nedu.store.user;

import com.nedu.store.order.Basket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NonNull String login;
    @NonNull String password;
    Basket basket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}