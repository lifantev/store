package com.nedu.store.user;

import com.nedu.store.user.dao.UserDao;
import org.apache.log4j.Logger;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger("userService");

    private final UserDao userDao;

    private User currentUser;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signUp(User user) {
        if (user.getPassword().length() < 3) {
            throw new RuntimeException("Short password");
        }

        if (userDao.getUserByLogin(user.getLogin()) != null) {
            throw new RuntimeException("User with the same login="
                    + user.getLogin() + " already exists");
        }

        currentUser = userDao.createUser(user);
    }

    @Override
    public User signIn(User user) {
        User stored = userDao.getUserByLogin(user.getLogin());
        if (stored != null) {
            throw new RuntimeException("User with login="
                    + user.getLogin() + " doesn't exist");
        }

        if (!Objects.equals(stored.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password or login");
        }

        currentUser = stored;
        return stored;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
