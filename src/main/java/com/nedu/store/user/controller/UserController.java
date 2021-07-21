package com.nedu.store.user.controller;

import com.nedu.store.user.User;

public interface UserController {
    void signUpUser(User user);
    void signInUser(User user);
    /*void signInUser(String login, String password);*/
}
