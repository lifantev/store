package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.user.UserDto;
import com.nedu.store.user.UserEntity;
import com.nedu.store.user.UserMapper;
import com.nedu.store.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            log.warn("User with the same login="
                    + userDto.getLogin() + " already exists");
            throw new RestException(RestExceptionEnum.ERR_007);
        }

        // TODO encode
        userRepository.saveAndFlush(userMapper.toEntity(userDto));

        log.info("User=" + userDto + " signed up");
        return userDto;
    }

    @Override
    public UserDto signIn(UserDto userDto) throws RestException {
        log.debug("User signing in");

        Optional<UserEntity> stored = userRepository.findByLogin(userDto.getLogin());
        if (stored.isEmpty()) {
            log.warn("User with login="
                    + userDto.getLogin() + " doesn't exist");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        // TODO decode
        if (!Objects.equals(stored.get().getPassword(), userDto.getPassword())) {
            log.warn("Wrong password or login");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        log.info("User=" + stored + " signed in");
        return userMapper.toDto(stored.get());
    }

    @Override
    public UserDto getUser(long id) {
        return userMapper.toDto(userRepository.findById(id).get());
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login).get());
    }
}
