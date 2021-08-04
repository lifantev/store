package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.UserDto;

public interface UserService {
    UserDto signUp(UserDto userDto) throws RestException;
    UserDto signIn(UserDto userDto) throws RestException;
    UserDto getUser(long id);
    UserDto getUserByLogin(String login);
}
