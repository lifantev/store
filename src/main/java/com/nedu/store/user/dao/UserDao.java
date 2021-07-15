package com.nedu.store.user.dao;

import com.nedu.store.user.User;

public interface UserDao {
    User createUser(User user);
    User getUser(long id);
    User getUserByLogin(String login);
    void deleteUser(long id);
}
