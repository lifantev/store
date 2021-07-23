package com.nedu.store.user.controller;

import com.nedu.store.user.User;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<User> signUpUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.signUp(user), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{login}")
    public ResponseEntity<User> signInUser(@PathVariable String login, @RequestBody String password) {
        return new ResponseEntity<>(userService.signIn(User.builder()
                .login(login)
                .password(password)
                .build()), HttpStatus.OK);
    }
}