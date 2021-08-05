package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.UserDto;

public interface UserService {
    Long signUp(UserDto userDto) throws RestException;
    Long signIn(UserDto userDto) throws RestException;
}
