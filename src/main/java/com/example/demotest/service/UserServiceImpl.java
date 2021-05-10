package com.example.demotest.service;

import com.example.demotest.exception.UserNotFoundException;
import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    private final ApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository repository, ApplicationContext context) {
        this.userRepository = repository;
        this.context = context;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#user.name")
    public User createOrReturnCached(User user) {
        logger.info("creating user: {}", user);
        return userRepository.save(user);
    }

    @Override
    @CachePut(value = "users", key = "#user.name")
    public User createAndRefreshCache(User user) {
        logger.info("creating user: {}",user);
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#name")
    public User save(String name, String email){
        logger.info("creating user with parameters: {} {}",name,email);
        return userRepository.save(new User(name,email));
    }

    @Override
    @Cacheable("users")
    public User get(int id) {
        logger.info("getting user by id: {}", id);
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(int id) {
        logger.info("deleting user by id: {}",id);
        userRepository.deleteById(id);

    }

    @Override
    @CacheEvict("users")
    public void deleteAndEvict(int id) {
        logger.info("deleting user by id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, int id) {
        User userFromDb = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User does not exists"));
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        return userRepository.save(userFromDb);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Cacheable(value = "users", key = "#name")
    public User saveByEmail(String name, String email) {
        logger.info("creating user with parameters: {} {}", name, email);
        return userRepository.save(new User(name,email));
    }

}
