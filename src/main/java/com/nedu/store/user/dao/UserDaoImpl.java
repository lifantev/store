package com.nedu.store.user.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.Basket;
import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service("userDao")
public class UserDaoImpl implements UserDao {

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

        user.setId(toStore.getId());
        user.setBasketId(toStore.getBasketId());
        log.trace("User created=" + user);
        return user;
    }

    @Override
    public User getUser(long id) {
        User stored = users.get(id);

        if (null == stored) {
            log.warn("Get user with id=" + id + " was failed");
            throw new RuntimeException("There is no user with id=" + id);
        }

        log.trace("User accessed=" + stored);
        return User.builder()
                .id(stored.getId())
                .login(stored.getLogin())
                .password(stored.getPassword())
                .basketId(stored.getBasketId())
                .build();
    }

    @Override
    public User getUserByLogin(String login) {
        var userStream = users.values().stream().filter(u -> Objects.equals(u.getLogin(), login));

        User stored = null;
        try {
            stored = userStream.findFirst().get();
        } catch (Exception e) {
            log.debug("User with login=" + login + " wasn't found!");
        }
        return stored;
    }
}
