package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.UserDTO;

public interface UserService {
    UserDTO signUp(UserDTO userDto) throws RestException;
    UserDTO signIn(UserDTO userDto) throws RestException;
    UserDTO getUser(long id);
    UserDTO getUserByLogin(String login);
}
