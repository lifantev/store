package com.nedu.store.user;

import com.nedu.store.user.dao.UserDao;
import org.apache.log4j.Logger;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger("userService");

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;

        LOGGER.debug("User service created");
    }

    @Override
    public User signUp(User user) {
        LOGGER.debug("User signing up");

        if (user.getPassword().length() < 3) {
            LOGGER.info("Short password (must be 4 symbols)");
            throw new RuntimeException();
        }

        if (userDao.getUserByLogin(user.getLogin()) != null) {
            LOGGER.info("User with the same login="
                    + user.getLogin() + " already exists");
            throw new RuntimeException();
        }

        user = userDao.createUser(user);
        LOGGER.info("User=" + user + " signed up");
        return user;
    }

    @Override
    public User signIn(User user) {
        LOGGER.debug("User signing in");

        User stored = userDao.getUserByLogin(user.getLogin());
        if (stored == null) {
            LOGGER.info("User with login="
                    + user.getLogin() + " doesn't exist");
            throw new RuntimeException();
        }

        if (!Objects.equals(stored.getPassword(), user.getPassword())) {
            LOGGER.info("Wrong password or login");
            throw new RuntimeException();
        }

        LOGGER.info("User=" + stored + " signed in");
        return stored;
    }
}
