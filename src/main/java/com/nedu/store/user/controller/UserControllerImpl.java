package com.nedu.store.user.controller;

import com.nedu.store.user.User;
import com.nedu.store.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public void signUpUser(@RequestBody User user) {
        userService.signUp(user);
    }

    @Override
    @GetMapping("/{login}")
    public void signInUser(@PathVariable String login, @RequestBody String password) {
        userService.signIn(User.builder()
                .login(login)
                .password(password)
                .build());
    }
}