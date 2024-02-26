package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Long userId);

    void enableUserById(Long userId);

    void disableUserById(Long userId);
}
