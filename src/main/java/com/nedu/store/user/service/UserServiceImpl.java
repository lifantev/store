package com.nedu.store.user.service;

import com.nedu.store.exceptions.RestException;
import com.nedu.store.exceptions.RestExceptionEnum;
import com.nedu.store.user.UserDto;
import com.nedu.store.user.UserEntity;
import com.nedu.store.user.UserMapper;
import com.nedu.store.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("User service created");
    }

    @Override
    public Long signUp(UserDto userDto) throws RestException {
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

        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity = userRepository.saveAndFlush(userEntity);

        log.info("User id=" + userEntity.getId() + " signed up");
        return userEntity.getId();
    }

    @Override
    public Long signIn(UserDto userDto) throws RestException {
        log.debug("User signing in");

        Optional<UserEntity> stored = userRepository.findByLogin(userDto.getLogin());

        if (stored.isEmpty()) {
            log.warn("User with login="
                    + userDto.getLogin() + " doesn't exist");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        if (!passwordEncoder.matches(userDto.getPassword(), stored.get().getPassword())) {
            log.warn("Wrong password or login");
            throw new RestException(RestExceptionEnum.ERR_001);
        }

        log.info("User=" + stored + " signed in");
        return stored.get().getId();
    }
}
