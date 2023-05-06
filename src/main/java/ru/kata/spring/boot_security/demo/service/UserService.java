package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User showId(Long id);

    void deleteUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    User findUser(String username);
}
