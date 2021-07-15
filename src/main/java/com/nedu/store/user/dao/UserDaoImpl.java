package com.nedu.store.user.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.Basket;
import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.user.User;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger("userDao");

    private final Map<Long, User> users = new HashMap<>();
    private final IdGenerator idGenerator;
    private final OrderDao orderDao;

    public UserDaoImpl(IdGenerator idGenerator, OrderDao orderDao) {
        this.idGenerator = idGenerator;
        this.orderDao = orderDao;
    }

    @Override
    public User createUser(User user) {
        long id = idGenerator.getId();

        User toStore = User.builder()
                .id(id)
                .login(user.getLogin())
                .password(user.getPassword())
                .basketId(orderDao.createBasket(Basket.builder().build()).getId())
                .build();

        users.put(id, toStore);

        user.setId(id);
        return user;
    }

    @Override
    public User getUser(long id) {
        User stored = users.get(id);

        if (stored == null) {
            throw new RuntimeException("There is no user with id=" + id);
        }

        return User.builder()
                .id(stored.getId())
                .login(stored.getLogin())
                .password(stored.getPassword())
                .basketId(stored.getBasketId())
                .build();
    }

    @Override
    public User getUserByLogin(String login) {
        var userStream = users.entrySet().stream().map(e -> e.getValue()).filter(u -> u.getLogin().equals(login));
        User stored = userStream.findFirst().get();

        return User.builder()
                .id(stored.getId())
                .login(stored.getLogin())
                .password(stored.getPassword())
                .basketId(stored.getBasketId())
                .build();
    }

    @Override
    public void deleteUser(long id) {
        users.remove(id);
    }
}
