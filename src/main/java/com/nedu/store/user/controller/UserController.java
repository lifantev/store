package com.nedu.store.user.controller;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.User;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<User> signUpUser(User user) throws RestException;
    ResponseEntity<User> signInUser(String login, String password) throws RestException;
}
