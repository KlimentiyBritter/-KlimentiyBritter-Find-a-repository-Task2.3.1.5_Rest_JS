package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getListOfUsers();
    void createNewUser(User user);
    User readUserById(long id);
    void updateUser(User user);
    void deleteById(long id);
    User findUsername (String username);
}
