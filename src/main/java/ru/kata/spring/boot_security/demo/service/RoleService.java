package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getRole();
    Optional<Role> getRoleID(Long id);
    void addNewRole(Role role);
}
