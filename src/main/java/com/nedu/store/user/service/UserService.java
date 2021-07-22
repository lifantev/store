package com.nedu.store.user.service;

import com.nedu.store.user.User;

public interface UserService {
    User signUp(User user);
    User signIn(User user);
    User getUser(long id);
    User getUserByLogin(String login);
}
