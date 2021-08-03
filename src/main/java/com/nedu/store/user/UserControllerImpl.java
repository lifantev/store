package com.nedu.store.user;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserControllerImpl {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> signUpUser(@RequestBody UserDTO userDto) throws RestException {
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDTO> signInUser(@PathVariable String login, @RequestBody String password) throws RestException {
        return new ResponseEntity<>(userService.signIn(UserDTO.builder()
                .login(login)
                .password(password)
                .build()), HttpStatus.OK);
    }
}