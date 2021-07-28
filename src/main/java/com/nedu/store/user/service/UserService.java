package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.User;

public interface UserService {
    User signUp(User user) throws RestException;
    User signIn(User user) throws RestException;
    User getUser(long id);
    User getUserByLogin(String login);
}
