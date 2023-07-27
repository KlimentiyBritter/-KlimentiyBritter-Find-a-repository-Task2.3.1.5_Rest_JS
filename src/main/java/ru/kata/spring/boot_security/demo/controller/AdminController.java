package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String index(Model model){
        //get all users from DAO & setting and we will pass on the display and presentation
        model.addAttribute("users", userService.getListOfUsers());
        return "show";
    }
    @GetMapping("/users")
    public String read(@RequestParam("param1") Integer id, Model model) {
        //Получим одного человека по ИД из ДАО и передадим на отображение в представление
        model.addAttribute("param1", userService.readUserById(id));
//        model.addAttribute("user", user);
        return "show";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "new";
    }
    @PostMapping()      //\\
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users";       //Переход на страницу списка
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userService.readUserById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}