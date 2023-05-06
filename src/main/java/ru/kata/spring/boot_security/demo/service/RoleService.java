package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void addRole(Role role);

    Optional<Role> findRoleById(Long id);

    void deleteRoleById(Long id);

    List<Role> getAllRoles();

    void updateRole(Role role);
}
