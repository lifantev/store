package com.nedu.store.user.dao;

import com.nedu.store.idgenerator.IdGenerator;
import com.nedu.store.order.BasketDTO;
import com.nedu.store.order.dao.OrderDao;
import com.nedu.store.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service("userDao")
public class UserDaoImpl implements UserDao {

    private final Map<Long, UserDTO> users = new ConcurrentHashMap<>() {{
        put(1L, UserDTO.builder()
                .id(1)
                .login("admin")
                .password("admin")
                .basketId(1)
                .build());
    }};

    private final IdGenerator idGenerator;
    private final OrderDao orderDao;

    public UserDaoImpl(IdGenerator idGenerator, OrderDao orderDao) {
        this.idGenerator = idGenerator;
        this.orderDao = orderDao;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        long id = idGenerator.getId();

        UserDTO toStore = UserDTO.builder()
                .id(id)
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .basketId(orderDao.createBasket(BasketDTO.builder().build()).getId())
                .build();

        users.put(id, toStore);

        userDto.setId(toStore.getId());
        userDto.setBasketId(toStore.getBasketId());
        log.trace("User created=" + userDto);
        return userDto;
    }

    @Override
    public UserDTO getUser(long id) {
        UserDTO stored = users.get(id);

        if (null == stored) {
            log.warn("Get user with id=" + id + " was failed");
            throw new RuntimeException("There is no user with id=" + id);
        }

        log.trace("User accessed=" + stored);
        return UserDTO.builder()
                .id(stored.getId())
                .login(stored.getLogin())
                .password(stored.getPassword())
                .basketId(stored.getBasketId())
                .build();
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        var userStream = users.values().stream().filter(u -> Objects.equals(u.getLogin(), login));

        UserDTO stored = null;
        try {
            stored = userStream.findFirst().get();
        } catch (Exception e) {
            log.debug("User with login=" + login + " wasn't found!");
        }
        return stored;
    }
}
