package com.example.bookstore.controllers;

import com.example.bookstore.models.User;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return "Email already registered!";
        }
        userService.registerUser(user);
        return "User registered successfully!";
    }
}
