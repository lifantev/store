package com.nedu.store.user.service;

import com.nedu.store.user.User;
import com.nedu.store.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("User service created");
    }

    @Override
    public User signUp(User user) {
        log.debug("User signing up");

        if (user.getPassword().length() < 3) {
            log.warn("Short password (must be 4 symbols)");
            throw new RuntimeException();
        }

        if (userDao.getUserByLogin(user.getLogin()) != null) {
            log.warn("User with the same login="
                    + user.getLogin() + " already exists");
            throw new RuntimeException();
        }

        user = userDao.createUser(user);
        log.info("User=" + user + " signed up");
        return user;
    }

    @Override
    public User signIn(User user) {
        log.debug("User signing in");

        User stored = userDao.getUserByLogin(user.getLogin());
        if (null == stored) {
            log.info("User with login="
                    + user.getLogin() + " doesn't exist");
            throw new RuntimeException();
        }

        if (!Objects.equals(stored.getPassword(), user.getPassword())) {
            log.info("Wrong password or login");
            throw new RuntimeException();
        }

        log.info("User=" + stored + " signed in");
        return stored;
    }
}
