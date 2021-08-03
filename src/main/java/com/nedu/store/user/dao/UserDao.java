package com.nedu.store.user.dao;

import com.nedu.store.user.UserDTO;

public interface UserDao {
    UserDTO createUser(UserDTO userDto);
    UserDTO getUser(long id);
    UserDTO getUserByLogin(String login);
}
