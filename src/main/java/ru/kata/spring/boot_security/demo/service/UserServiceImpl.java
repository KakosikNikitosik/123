package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String username) {
        User user = findUser(username);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        User user = findById(updatedUser.getId());
        String password = user.getPassword();
        if (updatedUser.getPassword().isEmpty()) {
            updatedUser.setPassword(password);
        } else if (updatedUser.getPassword().equals(password)) {
            userRepository.save(updatedUser);
        } else {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(updatedUser);

    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();

    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}
