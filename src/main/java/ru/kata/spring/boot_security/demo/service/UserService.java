package ru.kata.spring.boot_security.demo.service;


import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User showId(Long id);

    void deleteUserById(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

    User findById(Long id);

    User findUser(String username);
}
