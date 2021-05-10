package com.example.demotest.service;

import com.example.demotest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(int id);
    User save(User user);
    void deleteById(int id);
    User update(User user, int id);
    List<User> findAll();

    User save(String name, String email);
    User createOrReturnCached(User user);
    User createAndRefreshCache(User user);
    User get(int id);
    List<User> getAll();
    void delete(int id);
    void deleteAndEvict(int id);


    //  User saveByEmail(String name, String email);
}
