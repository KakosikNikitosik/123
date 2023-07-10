package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    private final UserService userService;

    @Autowired
    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/user")
    public ResponseEntity<User> getUserPage(Principal principal) {
        User user = userService.findUser(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admin")
    public ResponseEntity<HttpStatus> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
