package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/main")
    public ResponseEntity<Resource> getView() {
        Resource resource = new ClassPathResource("templates/RestView.html");
        HttpHeaders headersOfView = new HttpHeaders();
        headersOfView.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<>(resource, headersOfView, HttpStatus.OK);
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/infoUser")
    public ResponseEntity<Boolean> showUser(Principal principal) {
        return ResponseEntity.ok(userService.isUserExistWithEmail(principal.getName()));
    }
}
