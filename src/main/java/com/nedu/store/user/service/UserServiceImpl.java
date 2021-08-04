package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.user.UserDto;
import com.nedu.store.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("User service created");
    }

    @Override
    public UserDto signUp(UserDto userDto) throws RestException {
        log.debug("User signing up");

        if (userDto.getPassword().length() < 3) {
            log.warn("Short password(must be 4 symbols)");
            throw new RestException(RestExceptionEnum.ERR_006);
        }

        if (userRepository.findByLogin(userDto.getLogin()).isEmpty()) {
            log.warn("User with the same login="
                    + userDto.getLogin() + " already exists");
            throw new RestException(RestExceptionEnum.ERR_007);
        }

        userDto = userRepository.save(userDto);
        log.info("User=" + userDto + " signed up");
        return userDto;
    }

    @Override
public UserDto signIn(UserDto userDto) throws RestException {
        log.debug("User signing in");

        UserDto stored = userRepository.findByLogin(userDto.getLogin());
        if (null == stored) {
            log.warn("User with login="
                    + userDto.getLogin() + " doesn't exist");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        if (!Objects.equals(stored.getPassword(), userDto.getPassword())) {
            log.warn("Wrong password or login");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        log.info("User=" + stored + " signed in");
        return stored;
    }

    @Override
    public UserDto getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
