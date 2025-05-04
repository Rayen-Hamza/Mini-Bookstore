package com.example.bookstore.services;

import com.example.bookstore.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>(); // Keyed by email

    public void registerUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User findByEmail(String email) {
        return users.get(email);
    }

    public boolean emailExists(String email) {
        return users.containsKey(email);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
