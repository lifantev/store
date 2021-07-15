package com.nedu.store.user;

public interface UserService {
    void signUp(User user);
    User signIn(User user);
    User getCurrentUser();
}
