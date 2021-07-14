package com.nedu.store.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private Set<User> userSet = new LinkedHashSet<>();
    private User mainUser;

    @Override
    public boolean signUp(User user) {
        boolean signUpOk = userSet.add(user);
        if (signUpOk) {
            mainUser = user;
        }
        return signUpOk;
    }

    @Override
    public User signIn(User user) {
        if (userSet.contains(user)) {
            mainUser = user;
            return user;
        } else {
            return null;
        }
    }
}
