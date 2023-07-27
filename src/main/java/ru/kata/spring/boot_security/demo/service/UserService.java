package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getListOfUsers();
    boolean createUser(User user);
    User readUserById(long id);
    void update(User user);
    void deleteById(long id);
    User findUsername (String username);
}
