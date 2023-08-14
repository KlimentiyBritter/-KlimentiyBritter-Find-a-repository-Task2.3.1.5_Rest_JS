package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService extends UserDetailsService {
    Iterable<User> getListOfUsers();
    User createNewUser(User user);
    User readUserById(long id);
    User updateUser(User user);
    void deleteById(long id);
    void findUser (User user, BindingResult bindingResult);
    Boolean isUserExistWithEmail(String email);
}
