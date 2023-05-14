package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DbInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void postConstruct() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        roleService.addRole(user);
        roleService.addRole(admin);

        User user1 = new User("admin", "admin", "admin@mail.ru", new HashSet<>(Collections.singleton(admin)));
        userService.addUser(user1);
        User user2 = new User("user", "user", "user@mail.ru", new HashSet<>(Collections.singleton(user)));
        userService.addUser(user2);

    }
}
