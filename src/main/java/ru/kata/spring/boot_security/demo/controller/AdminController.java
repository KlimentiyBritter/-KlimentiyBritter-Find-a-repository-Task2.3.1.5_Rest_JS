package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
//@org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/main")
    public ResponseEntity<Resource> getView() {
        Resource resource = new ClassPathResource("templates/RestView.html");
        HttpHeaders headersOfView = new HttpHeaders();
        headersOfView.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(resource, headersOfView, HttpStatus.OK);
    }
    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }
    @GetMapping("/api/roles/management")
    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }
    @GetMapping("/api/users/management")
    public Iterable<User> getUsers() {
        return userService.getListOfUsers();
    }
    @GetMapping("/api/users/auth")
    public User getAllUsers(@AuthenticationPrincipal User user) {
        return userService.readUserById(user.getId());
    }
    @GetMapping("/api/users/management/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.readUserById(id);
    }
    @PostMapping("/api/users/management")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println(user.getUsername());
        User savedUser = userService.createNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PatchMapping("/api/users/management/{id}")
    public User updateUser(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println("Updating!!!");
        return userService.updateUser(user);
    }
    @DeleteMapping("/api/users/management/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }
}