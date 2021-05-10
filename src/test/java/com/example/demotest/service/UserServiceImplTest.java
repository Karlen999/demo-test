package com.example.demotest.service;

import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);


    @Test
    void save() {
        createAndPrint("poxos","poxos@mail.com");
        createAndPrint("poxos","petros@mail.com");
        createAndPrint("martiros","poxos@mail.com");

        logger.info("all entries are below:");
        userService.getAll().forEach(user -> logger.info("{}", user.toString()));
    }

    @Test
    void createAndRefresh() {
        User user1 = userService.createOrReturnCached(new User("poxos","poxos@mail.ru"));
        logger.info("created user1: {}", user1);

        User user2 = userService.createOrReturnCached(new User("poxos","urish@mail.ru"));
        logger.info("created user2: {}", user2);

        User user3 = userService.createAndRefreshCache(new User("poxos","mail"));
        logger.info("created user3: {}", user3);

        User user4 = userService.createAndRefreshCache(new User("poxos", "petros@mail.ru"));
        logger.info("created user4: {}", user4);
    }

    @Test
    void get() {
        User user = User.builder()
                .id(1)
                .name("poxos")
                .email("poxos@mail.com")
                .password("poxos").build();
        User user1 = User.builder()
                .id(2)
                .name("poxosik")
                .email("poxosik@mail.com")
                .password("poxosik").build();

        getAndPrint(user.getId());
        getAndPrint(user1.getId());
        getAndPrint(user.getId());
        getAndPrint(user1.getId());
    }

    private void getAndPrint(int id) {
        User user = User.builder()
                .id(1)
                .name("poxos")
                .email("poxos@mail.com")
                .password("poxos").build();
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        User user1 = User.builder()
                .id(2)
                .name("petros")
                .email("petros@mail.com")
                .password("petros").build();
        when(userService.findById(user1.getId())).thenReturn(Optional.of(user1));
        logger.info("user found: {}", userService.get(id));
    }

    public void createAndPrint(String name, String email){
        User user = User.builder()
                .name("poxos")
                .email("poxos@mail.com").build();
        User user1 = User.builder()
                .name("poxos")
                .email("poxosik@mail.com").build();
        User user2 = User.builder()
                .name("martiros")
                .email("poxos@mail.com").build();
        List<User> users = Arrays.asList(user,user1,user2);
        when(userService.findAll()).thenReturn(users);
        logger.info("created user: {}", userService.save(name,email));
    }

    @Test
    void delete() {
        User user1 = User.builder()
                .id(1)
                .name("poxos")
                .email("poxos@mail.com")
                .password("poxos").build();

        when(userService.findById(user1.getId())).thenReturn(Optional.of(user1));
        logger.info("{}", userService.get(user1.getId()));

        User user2 = User.builder()
                .id(2)
                .name("poxos")
                .email("poxos@mail.com")
                .password("poxos").build();
        when(userService.findById(user2.getId())).thenReturn(Optional.of(user2));
        logger.info("{}", userService.get(user2.getId()));

        userService.delete(user1.getId());
        userService.deleteAndEvict(user2.getId());

        logger.info("{}", userService.get(user1.getId()));
        logger.info("{}", userService.get(user2.getId()));
    }

}