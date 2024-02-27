package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.User;
import com.sitblueprint.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    public User getUser(@Param("userId") String userId) {
        return userService.getUserById(Long.parseLong(userId));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(String customerId) {
        userService.deleteUserById(Long.parseLong(customerId));
    }

    @PostMapping("enable/{userId}")
    public void enableUser(@PathVariable("userId") String userId) {
        userService.enableUserById(Long.parseLong(userId));
    }

    @PostMapping("disable/{userId}")
    public void disableUser(@PathVariable("userId") String userId) {
        userService.disableUserById(Long.parseLong(userId));
    }
}