package com.nedu.store.user;

public interface UserService {
    boolean signUp(User user);
    User signIn(User user);
}
