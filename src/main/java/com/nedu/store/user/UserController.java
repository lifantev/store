package com.nedu.store.user;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> signUpUser(@RequestBody UserDto userDto) throws RestException {
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.CREATED);
    }

    /*@GetMapping("/{login}")
    public ResponseEntity<Long> signInUser(@PathVariable String login, @RequestBody String password) throws RestException {
        return new ResponseEntity<>(userService.signIn(UserDto.builder()
                .login(login)
                .password(password)
                .build()), HttpStatus.OK);
    }*/
}