package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository, RoleService roleService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    @GetMapping()
    public String indexOfUsers(Model model, Principal principal){
        List<User> users = userService.getListOfUsers();
        User newUser = userRepository.find(principal.getName());
        model.addAttribute("newUser", newUser);
        model.addAttribute("users", users);
        model.addAttribute("allRole", roleService.getRole());
        return "show";      //users
    }
    @GetMapping("/new")
    public String enterNewUser(Model model, Principal principal){
        User newUser = userRepository.find(principal.getName());
        model.addAttribute("newUser", newUser);
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRole", roleService.getRole());
        return "new";
    }
    @PostMapping("/save")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.createNewUser(user);
        return "redirect:/admin/";       //Переход на страницу списка
    }
    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(value="id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }
}